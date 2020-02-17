package com.competition.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.user.AuthenticationToken;
import com.competition.user.service.UserService;

@RestController
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManaget;
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public AuthenticationToken Login(@ModelAttribute(name="username") String username, @ModelAttribute(name="pw") String pw, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (username, pw);
		Authentication authentication = authenticationManaget.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                  SecurityContextHolder.getContext());
		
		UserDetails user = userService.loadUserByUsername(username);
        return new AuthenticationToken(user.getUsername(), user.getAuthorities(), session.getId());
	}
	
	@PostMapping("/logout")
	public Object Logout() {
		return new Object();
	}
}