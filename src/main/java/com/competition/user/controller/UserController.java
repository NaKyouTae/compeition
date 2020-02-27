package com.competition.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.User;
import com.competition.jpa.model.UserMappingRole;
import com.competition.jpa.repository.UserMappingRoleRepository;
import com.competition.jpa.repository.UserRepository;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMappingRoleRepository userMappingRoleRepository;
	
	@GetMapping("users")
	public ControllerResponse<List<User>> Users() throws Exception {
		ControllerResponse<List<User>> res = new ControllerResponse<List<User>>();

		try {
			res.setResult(userRepository.findAll());
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get User Lists :) ");
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
	@GetMapping("userinfo")
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
	
	@GetMapping("userrole")
	public ControllerResponse<List<UserMappingRole>> UserRole(@ModelAttribute("username") String username) throws Exception {
		ControllerResponse<List<UserMappingRole>> res = new ControllerResponse<List<UserMappingRole>>();
		
		try {
			res.setResult(userMappingRoleRepository.findByUsername(username));
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get User Role :) ");
		} catch (Exception e) {
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}
		
		return res;
	}
	
}
