package com.competition.controller.user;

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
import com.competition.service.user.UserService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMappingRoleRepository userMappingRoleRepository;
	
	@GetMapping("/lists")
	public ControllerResponse<List<User>> getLists() throws Exception {
		ControllerResponse<List<User>> res = new ControllerResponse<List<User>>();

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
	
	@GetMapping("/userinfo")
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
	
	@GetMapping("/userrole")
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
