package com.competition.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.Menu;
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
		ControllerResponse<List<Menu>> res = new ControllerResponse<List<Menu>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Menu List :) "); 
			res.setResult(menuService.getList());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/routes")
	public <T extends Object> T getRouteMenu() throws Exception {
		ControllerResponse<List<Menu>> res = new ControllerResponse<List<Menu>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Routes Menu :) "); 
			res.setResult(menuService.getRouteMenu());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/levels")
	public <T extends Object> T getListByLevel(String pidx) throws Exception  {
		ControllerResponse<List<Menu>> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Children List :) ");
			res.setResult(menuService.getListByLevel(pidx));
		}catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/menus/{idx}")
	public <T extends Object> T seMenu(String idx) throws Exception {
		ControllerResponse<List<Menu>> res = new ControllerResponse<List<Menu>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search One Menu :) "); 
			res.setResult(menuService.seMenu(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("/menus")
	public <T extends Object> T inMenu(@RequestBody MenuVO menu) throws Exception {
		ControllerResponse<List<Menu>> res = new ControllerResponse<List<Menu>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Menu :) "); 
			res.setResult(menuService.inMenu(menu));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PutMapping("/menus/{idx}")
	public <T extends Object> T upMenu(@RequestBody MenuVO menu) throws Exception {
		ControllerResponse<List<Menu>> res = new ControllerResponse<List<Menu>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Menu :) "); 
			res.setResult(menuService.upMenu(menu));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@DeleteMapping("/menus/{idx}")
	public <T extends Object> T deMenu(@RequestBody MenuVO menu) throws Exception{
		ControllerResponse<List<Menu>> res = new ControllerResponse<List<Menu>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Menu :) "); 
			res.setResult(menuService.deMenu(menu));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
}
