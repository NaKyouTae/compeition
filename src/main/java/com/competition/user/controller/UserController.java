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
	public ControllerResponse<List<User>> Users() {
		ControllerResponse<List<User>> res = new ControllerResponse<List<User>>();

		res.setResult(userRepository.findAll());
		res.setResultCode(HttpStatus.OK);
		res.setMessage("Success Get User Lists :) ");
		
		return res;
	}
	
	@GetMapping("userinfo")
	public ControllerResponse<User> UserInfo(@ModelAttribute("username") String username) {
		ControllerResponse<User> res = new ControllerResponse<User>();

		res.setResult(userRepository.findByUsername(username));
		res.setResultCode(HttpStatus.OK);
		res.setMessage("Success Get User Info :) ");
		
		return res;
	}
	
	@GetMapping("userrole")
	public ControllerResponse<List<UserMappingRole>> UserRole(@ModelAttribute("username") String username) {
		ControllerResponse<List<UserMappingRole>> res = new ControllerResponse<List<UserMappingRole>>();

		res.setResult(userMappingRoleRepository.findByUsername(username));
		res.setResultCode(HttpStatus.OK);
		res.setMessage("Success Get User Role :) ");
		
		return res;
	}
	
}
