package com.competition.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public <T extends Object> T Users() {
		return (T) userRepository.findAll();
	}
	
	@GetMapping("userinfo")
	public <T extends Object> T UserInfo(@ModelAttribute("username") String username) {
		return (T) userRepository.findByUsername(username);
	}
	
	@GetMapping("userrole")
	public <T extends Object> T UserRole(@ModelAttribute("username") String username) {
		return (T) userMappingRoleRepository.findByUsername(username);
	}
	
}
