package com.competition.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.competition.user.AuthenticationToken;
import com.competition.user.service.UserService;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private AuthenticationManager am;
	@Autowired
	private UserService userService;
	
	@CrossOrigin("*")
	@PostMapping("/login")
	public AuthenticationToken Login(@RequestParam(name="username", required = true) String username, @RequestParam(name="password", required = true) String password, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (username, password);
		Authentication authentication = am.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
	
		UserDetails user = userService.loadUserByUsername(username);
		System.out.println(new AuthenticationToken(user.getUsername(), user.getAuthorities(), session.getId()));
		
		return new AuthenticationToken(user.getUsername(), user.getAuthorities(), session.getId());
	}
	
	@GetMapping("/logout")
	public Object Logout() {
		return new Object();
	}
}