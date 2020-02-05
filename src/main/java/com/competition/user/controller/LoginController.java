package com.competition.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@GetMapping("/login")
	public Object Login(@RequestParam(name="username") String username, @RequestParam(name="pw") String pw) {
		return new Object();
	}
	
	@PostMapping("/logout")
	public Object Logout() {
		return new Object();
	}
}