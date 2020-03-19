package com.competition.process.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.Menu;
import com.competition.jpa.repository.MenuRepository;

@Component
@SuppressWarnings("unchecked")
public class MenuProcess {
	
	@Autowired
	private MenuRepository menuRepository;
	
	public <T extends Object> T getList() throws Exception {
		return (T) menuRepository.findAll();
	}
	
	public<T extends Object> T inMenu(Menu dto) throws Exception {
		return (T) menuRepository.save(dto);
	}
	
	public<T extends Object> T upMenu(Menu dto) throws Exception {
		return (T) menuRepository.save(dto);
	}
	public void deMenu(Menu dto) throws Exception {
		menuRepository.delete(dto);
	}
}