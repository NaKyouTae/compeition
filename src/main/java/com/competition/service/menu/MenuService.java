package com.competition.service.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.menu.Menu;
import com.competition.process.menu.MenuProcess;
import com.competition.util.ObjectUtil;
import com.competition.vo.menu.MenuVO;

@Service
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
		
		for(Menu a : allMenu) {
			MenuVO routeMenu = ObjectUtil.toObject(a, new MenuVO());
				
				routeMenu.setChild(true);
				routeMenu.setChildren(menuProcess.getListByLevel(a.getIdx()));
				
			result.add(routeMenu);
		}
		
		return (T) result;
	}
	
	public <T extends Object> T getListByLevel(String pidx) throws Exception {
		
		List<Menu> list = new ArrayList<>();
		
		if(pidx.equals("null")) {
			list = menuProcess.getListByLevelIsNull();
		}else {
			list = menuProcess.getListByLevel(pidx);
		}
		
		return (T) list;
	}
	
	public <T extends Object> T seMenu(String idx) throws Exception {
		return (T) menuProcess.seMenu(idx);
	}
	
	public <T extends Object> T inMenu(MenuVO vo) throws Exception {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		
		Menu dto = ObjectUtil.toObject(vo, new Menu());
		
		dto.setIdx(UUID.randomUUID().toString().replace("-", ""));
		dto.setInsertDate(now);

		if(vo.getParent() == null) {			
			dto.setParent("null");
		}else {
			
			Menu parentMenu = seMenu(vo.getParent());
			
			MenuVO parentVO = ObjectUtil.toObject(parentMenu, new MenuVO());
			
			parentVO.setChild(true);
			
			upMenu(parentVO);
			
			dto.setUrl(parentMenu.getUrl() + vo.getUrl());
			dto.setParent(vo.getParent());
		}
		
		return (T) menuProcess.inMenu(dto);
	}
	
	public <T extends Object> T upMenu(MenuVO vo) throws Exception {
		
		Menu dto = ObjectUtil.toObject(vo, new Menu());
		
		return (T) menuProcess.inMenu(dto);
	}
	
	public <T extends Object> T deMenu(MenuVO vo) throws Exception{
		try {
			
			Menu dto = ObjectUtil.toObject(vo, new Menu());
			
			menuProcess.deMenu(dto);

			List<Menu> children = getListByLevel(vo.getParent());
			
			if(children.size() == 0) {
				
				Menu parentMenu = seMenu(vo.getParent());
				MenuVO parentVO = ObjectUtil.toObject(parentMenu, new MenuVO());
				
				parentVO.setChild(false);
				
				upMenu(parentVO);
			}
			
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
