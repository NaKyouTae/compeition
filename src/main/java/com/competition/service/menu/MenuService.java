package com.competition.service.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.Menu;
import com.competition.process.menu.MenuProcess;
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
			MenuVO routeMenu = new MenuVO();
				
				
				routeMenu.setChild(true);
				routeMenu.setIdx(a.getIdx());
				routeMenu.setInsertdate(a.getInsertdate());
				routeMenu.setLevel(a.getLevel());
				routeMenu.setMenugroup(a.getMenugroup());
				routeMenu.setMenuorder(a.getMenuorder());
				routeMenu.setParent(a.getParent());
				routeMenu.setTitle(a.getTitle());
				routeMenu.setUrl(a.getUrl());
				routeMenu.setChildren(menuProcess.getListByLevel(a.getIdx()));			
				
			result.add(routeMenu);
		}
		
//		for(MenuVO r : result) {
//			if(!r.getLevel().equals(1)) {
//				result.remove(r);
//			}
//		}
		
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
		
		Menu dto = new Menu();
		
		dto.setIdx(UUID.randomUUID().toString().replace("-", ""));
		dto.setChild(vo.getChild());
		dto.setInsertdate(now);
		dto.setLevel(vo.getLevel());
		dto.setMenugroup(vo.getMenugroup());
		dto.setMenuorder(vo.getMenuorder());
		dto.setTitle(vo.getTitle());

		if(vo.getParent() == null) {			
			dto.setParent("null");
		}else {
			
			Menu parentMenu = seMenu(vo.getParent());
			
			MenuVO parentVO = new MenuVO();
			
			parentVO.setChild(true);
			parentVO.setIdx(parentMenu.getIdx());
			parentVO.setInsertdate(parentMenu.getInsertdate());
			parentVO.setLevel(parentMenu.getLevel());
			parentVO.setMenugroup(parentMenu.getMenugroup());
			parentVO.setMenuorder(parentMenu.getMenuorder());
			parentVO.setParent(parentMenu.getParent());
			parentVO.setTitle(parentMenu.getTitle());
			parentVO.setUrl(parentMenu.getUrl());
			
			upMenu(parentVO);
			
			dto.setUrl(parentMenu.getUrl() + vo.getUrl());
			dto.setParent(vo.getParent());
		}
		
		return (T) menuProcess.inMenu(dto);
	}
	
	public <T extends Object> T upMenu(MenuVO vo) throws Exception {
		
		Menu dto = new Menu();
		
		dto.setChild(vo.getChild());
		dto.setIdx(vo.getIdx());
		dto.setInsertdate(vo.getInsertdate());
		dto.setLevel(vo.getLevel());
		dto.setMenugroup(vo.getMenugroup());
		dto.setMenuorder(vo.getMenuorder());
		dto.setParent(vo.getParent());
		dto.setTitle(vo.getTitle());
		dto.setUrl(vo.getUrl());
		
		return (T) menuProcess.inMenu(dto);
	}
	
	public <T extends Object> T deMenu(MenuVO vo) throws Exception{
		try {
			
			Menu dto = new Menu();
			
			dto.setChild(vo.getChild());
			dto.setIdx(vo.getIdx());
			dto.setInsertdate(vo.getInsertdate());
			dto.setLevel(vo.getLevel());
			dto.setMenugroup(vo.getMenugroup());
			dto.setMenuorder(vo.getMenuorder());
			dto.setParent(vo.getParent());
			dto.setTitle(vo.getTitle());
			dto.setUrl(vo.getUrl());
			
			if(vo.getParent() != null) {
				
				Menu parentMenu = seMenu(vo.getParent());
				
				MenuVO parentVO = new MenuVO();
				
				parentVO.setChild(false);
				parentVO.setIdx(parentMenu.getIdx());
				parentVO.setInsertdate(parentMenu.getInsertdate());
				parentVO.setLevel(parentMenu.getLevel());
				parentVO.setMenugroup(parentMenu.getMenugroup());
				parentVO.setMenuorder(parentMenu.getMenuorder());
				parentVO.setParent(parentMenu.getParent());
				parentVO.setTitle(parentMenu.getTitle());
				parentVO.setUrl(parentMenu.getUrl());
				
				upMenu(parentVO);
			}
			
			
			menuProcess.deMenu(dto);
			
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
