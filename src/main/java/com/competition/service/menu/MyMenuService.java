package com.competition.service.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.menu.Menu;
import com.competition.jpa.model.menu.MyMenu;
import com.competition.process.menu.MyMenuProcess;
import com.competition.util.ObjectUtil;
import com.competition.vo.menu.MenuVO;

@Service
@SuppressWarnings("unchecked")
public class MyMenuService {
	
	@Autowired
	private MyMenuProcess myMenuProcess;
	
	public <T extends Object> T seMyMenus() throws Exception {
		return (T) myMenuProcess.seMyMenus();
	}
	
	public <T extends Object> T getListByLevel(String pidx) throws Exception {
		
		List<Menu> list = new ArrayList<>();
		
		if(pidx.equals("null")) {
			list = myMenuProcess.getListByLevelIsNull();
		}else {
			list = myMenuProcess.getListByLevel(pidx);
		}
		
		return (T) list;
	}
	
	public <T extends Object> T seMyMenu(String idx) throws Exception {
		return (T) myMenuProcess.seMyMenu(idx);
	}
	
	public <T extends Object> T inMyMenu(MenuVO vo) throws Exception {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		
		MyMenu dto = ObjectUtil.toObj(vo, new MyMenu());
		
		dto.setIdx(UUID.randomUUID().toString().replace("-", ""));
		dto.setInsertDate(now);

		if(vo.getParent() == null) {			
			dto.setParent("null");
		}else {
			
			MyMenu parentMenu = seMyMenu(vo.getParent());
			
			MenuVO parentVO = ObjectUtil.toObj(parentMenu, new MenuVO());
			
			parentVO.setChild(true);
			
			upMyMenu(parentVO);
			
			dto.setUrl(parentMenu.getUrl() + vo.getUrl());
			dto.setParent(vo.getParent());
		}
		
		return (T) myMenuProcess.inMyMenu(dto);
	}
	
	public <T extends Object> T upMyMenu(MenuVO vo) throws Exception {
		
		MyMenu dto = ObjectUtil.toObj(vo, new MyMenu());
		
		return (T) myMenuProcess.inMyMenu(dto);
	}
	
	public <T extends Object> T deMyMenu(MenuVO vo) throws Exception{
		try {
			
			MyMenu dto = ObjectUtil.toObj(vo, new MyMenu());
			
			myMenuProcess.deMyMenu(dto);

			List<Menu> children = getListByLevel(vo.getParent());
			
			if(children.size() == 0) {
				
				MyMenu parentMenu = seMyMenu(vo.getParent());
				MenuVO parentVO = ObjectUtil.toObj(parentMenu, new MenuVO());
				
				parentVO.setChild(false);
				
				upMyMenu(parentVO);
			}
			
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
