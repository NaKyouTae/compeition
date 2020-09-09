package com.mercury.service.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.menu.Menu;
import com.mercury.process.menu.MenuProcess;
import com.mercury.util.ObjectUtil;
import com.mercury.vo.menu.MenuVO;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class MenuService {

	@Autowired
	private MenuProcess menuProcess;

	public <T extends Object> T getList() throws Exception {
		return (T) menuProcess.getList();
	}

	public <T extends Object> T getRouteMenu() throws Exception {
		List<MenuVO> result = new ArrayList<>();

		List<Menu> allMenu = getList();

		for (Menu a : allMenu) {
			MenuVO routeMenu = ObjectUtil.toObj(a, new MenuVO());

			routeMenu.setChild(true);
			routeMenu.setChildren(menuProcess.getListByLevel(a.getIdx()));

			result.add(routeMenu);
		}

		return (T) result;
	}

	public <T extends Object> T getListByLevel(String pidx) throws Exception {

		List<Menu> list = new ArrayList<>();

		if (pidx.equals("null")) {
			list = menuProcess.getParentIsNullOrderByMenuOrderAsc();
		} else {
			list = menuProcess.getListByLevel(pidx);
		}

		return (T) list;
	}

	public <T extends Object> T seMenu(String idx) throws Exception {
		return (T) menuProcess.seMenu(idx);
	}

	public <T extends Object> T inMenu(MenuVO vo) throws Exception {
		DateTimeFormatter format = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);

		Menu dto = ObjectUtil.toObj(vo, new Menu());

		dto.setIdx(UUID.randomUUID().toString().replace("-", ""));
		dto.setInsertDate(now);

		if (vo.getParent() == null) {
			dto.setParent("null");
		} else {

			Menu parentMenu = seMenu(vo.getParent());

			MenuVO parentVO = ObjectUtil.toObj(parentMenu, new MenuVO());

			parentVO.setChild(true);

			upMenu(parentVO);

			dto.setUrl(parentMenu.getUrl() + vo.getUrl());
			dto.setParent(vo.getParent());
		}

		return (T) menuProcess.inMenu(dto);
	}

	public <T extends Object> T upMenu(MenuVO vo) throws Exception {

		Menu dto = ObjectUtil.toObj(vo, new Menu());

		return (T) menuProcess.inMenu(dto);
	}

	public <T extends Object> T deMenu(MenuVO vo) throws Exception {
		Menu dto = ObjectUtil.toObj(vo, new Menu());

		menuProcess.deMenu(dto);

		List<Menu> children = getListByLevel(vo.getParent());

		if (children.size() == 0) {

			Menu parentMenu = seMenu(vo.getParent());
			MenuVO parentVO = ObjectUtil.toObj(parentMenu, new MenuVO());

			parentVO.setChild(false);

			upMenu(parentVO);
		}

		return (T) Boolean.TRUE;
	}
}
