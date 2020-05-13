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
import com.competition.jpa.model.menu.MyMenu;
import com.competition.service.menu.MyMenuService;
import com.competition.vo.menu.MenuVO;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/mymenus")
public class MyMenuController {

	@Autowired
	private MyMenuService myMenuService;
	
	@GetMapping("/levels")
	public <T extends Object> T getListByLevel(String pidx) throws Exception  {
		ControllerResponse<List<MyMenu>> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get MyMenu Children List :) ");
			res.setResult(myMenuService.getListByLevel(pidx));
		}catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seMyMenu(String idx) throws Exception {
		ControllerResponse<List<MyMenu>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search One MyMenu :) "); 
			res.setResult(myMenuService.seMyMenu(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inMyMenu(@RequestBody MenuVO menu) throws Exception {
		ControllerResponse<MyMenu> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert MyMenu :) "); 
			res.setResult(myMenuService.inMyMenu(menu));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PutMapping("/{idx}")
	public <T extends Object> T upMyMenu(@RequestBody MenuVO menu) throws Exception {
		ControllerResponse<MyMenu> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update MyMenu :) "); 
			res.setResult(myMenuService.upMyMenu(menu));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@DeleteMapping("/{idx}")
	public <T extends Object> T deMyMenu(@RequestBody MenuVO menu) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete MyMenu :) "); 
			res.setResult(myMenuService.deMyMenu(menu));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
}
