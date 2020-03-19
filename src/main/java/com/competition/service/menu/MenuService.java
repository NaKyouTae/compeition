package com.competition.service.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	public <T extends Object> T inMenu(MenuVO vo) throws Exception {
		
		Menu dto = new Menu();
		
		dto.setIdx(UUID.randomUUID().toString());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		dto.setInsert_date(now);
		dto.setTitle(vo.getTitle());
		dto.setUrl(vo.getUrl());

		if(vo.getP_idx() == null) {			
			dto.setP_idx(null);
		}else {
			dto.setP_idx(vo.getP_idx());
		}
		
		return (T) menuProcess.inMenu(dto);
	}
	
	public <T extends Object> T upMenu(MenuVO vo) throws Exception {
		
		Menu dto = new Menu();
		
		dto.setIdx(vo.getIdx());
		dto.setInsert_date(vo.getInsert_date());
		dto.setTitle(vo.getTitle());
		dto.setUrl(vo.getUrl());
		
		if(vo.getP_idx().isEmpty()) {			
			dto.setP_idx(null);
		}else {
			dto.setP_idx(vo.getP_idx());
		}
		
		return (T) menuProcess.inMenu(dto);
	}
	
	public <T extends Object> T deMenu(MenuVO vo) throws Exception{
		try {
			
			Menu dto = new Menu();
			
			dto.setIdx(vo.getIdx());
			dto.setInsert_date(vo.getInsert_date());
			dto.setTitle(vo.getTitle());
			dto.setUrl(vo.getUrl());
			
			if(vo.getP_idx().isEmpty()) {			
				dto.setP_idx(null);
			}else {
				dto.setP_idx(vo.getP_idx());
			}
			
			menuProcess.deMenu(dto);
			
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
