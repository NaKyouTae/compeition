package com.competition.controller.role;

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
import com.competition.jpa.model.role.Role;
import com.competition.service.role.RoleService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public <T extends Object> T getRoles() throws Exception{
		ControllerResponse<List<Role>> res = new ControllerResponse<>();

		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Role List :) "); 
			res.setResult(roleService.getRoles());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T getRole(String idx) throws Exception{
		ControllerResponse<Role> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Role List :) "); 
			res.setResult(roleService.getRole(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inRole(@RequestBody Role role) throws Exception{
		ControllerResponse<Role> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Role :) "); 
			res.setResult(roleService.inRole(role));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PutMapping("/{idx}")
	public <T extends Object> T upRole(@RequestBody Role role) throws Exception{
		ControllerResponse<Role> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Role :) "); 
			res.setResult(roleService.upRole(role));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	@DeleteMapping("/{idx}")
	public <T extends Object> T deRole(@RequestBody Role role) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Role :) "); 
			res.setResult(roleService.deRole(role));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
}
