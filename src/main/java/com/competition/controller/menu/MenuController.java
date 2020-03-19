package com.competition.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.service.menu.MenuService;

@RestController
@RequestMapping("/service/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/lists")
	public <T extends Object> T getList() {
		return (T) menuService.getList();
	}
}
