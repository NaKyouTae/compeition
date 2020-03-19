package com.competition.process.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.repository.MenuRepository;

@Component
public class MenuProcess {
	
	@Autowired
	private MenuRepository menuRepository;
	
	public <T extends Object> T getList() {
		return (T) menuRepository.findAll();
	}
}
