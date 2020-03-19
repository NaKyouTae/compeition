package com.competition.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.process.menu.MenuProcess;

@Service
public class MenuService {
	
	@Autowired
	private MenuProcess menuProcess;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getList() {
		return (T) menuProcess.getList();
	}
}
