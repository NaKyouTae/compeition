package com.competition.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.service.menu.MenuService;
import com.competition.vo.menu.MenuVO;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/lists")
	public <T extends Object> T getList() throws Exception {
		return (T) menuService.getList();
	}
	
	@PostMapping("/menus")
	public <T extends Object> T inMenu(@RequestBody MenuVO menu) throws Exception {
		return (T) menuService.inMenu(menu);
	}
	
	@PutMapping("/menus/{menu.idx}")
	public <T extends Object> T upMenu(@RequestBody MenuVO menu) throws Exception {
		return (T) menuService.upMenu(menu);
	}
	
	@DeleteMapping("/menus/{idx}")
	public <T extends Object> T deMenu(@RequestBody MenuVO menu) throws Exception{
		return (T) menuService.deMenu(menu);
	}
	
}
