package com.competition.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserRole;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.user.UserRoleRepository;
import com.competition.service.user.UserService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleRepository userMappingRoleRepository;
	
	@GetMapping("/duplicate")
	public ControllerResponse<Boolean> checkUserName(String userName) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Check User Name :) ");
			res.setResult(userService.checkUserName(userName));
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
	@GetMapping("")
	public ControllerResponse<List<User>> getLists() throws Exception {
		ControllerResponse<List<User>> res = new ControllerResponse<>();

		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get User Lists :) ");
			res.setResult(userService.getLists());
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
	public <T extends Object> T seUserByIdx(String idx) throws Exception {
		ControllerResponse<User> res = new ControllerResponse<User>();
		try {
			res.setResult(userService.seUserByIdx(idx));
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search User By IDX :) ");
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		return (T) res;
	}
	
	@GetMapping("/{username}")
	public ControllerResponse<User> UserInfo(@ModelAttribute("username") String username) throws Exception {
		ControllerResponse<User> res = new ControllerResponse<User>();

		try {
			res.setResult(userRepository.findByUsername(username));
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get User Info :) ");
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
	@GetMapping("/role/{username}")
	public ControllerResponse<List<UserRole>> UserRole(@ModelAttribute("username") String username) throws Exception {
		ControllerResponse<List<UserRole>> res = new ControllerResponse<List<UserRole>>();
		
		try {
			res.setResult(userMappingRoleRepository.findByUserName(username));
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get User Role :) ");
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
	@PutMapping("/{idx}")
	public ControllerResponse<User> upUser(@RequestBody User user, @RequestBody(required = false) UserRole role) throws Exception{
		ControllerResponse<User> res = new ControllerResponse<User>();
		try {
			res.setResult(userService.upUser(user, role));
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update User :) ");
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res; 
	}
	
	@DeleteMapping("/{idx}")
	public ControllerResponse<Boolean> destoryUser(@RequestBody User user) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<Boolean>();
		try {
			res.setResult(userService.destoryUser(user));
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete User :) ");
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res; 
	}
	
}
