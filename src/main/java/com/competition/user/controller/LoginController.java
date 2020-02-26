package com.competition.user.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.competition.jpa.model.User;
import com.competition.jpa.repository.UserRepository;
import com.competition.user.AuthenticationToken;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private AuthenticationManager am;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@CrossOrigin("*")
	@PostMapping("/signup")
	public Object SignUp(@ModelAttribute(name = "signup") User user) {
		user.setInsert_date(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@CrossOrigin("*")
	@PostMapping("/login")
	public AuthenticationToken Login(@RequestParam(name="username", required = true) String username, @RequestParam(name="password", required = true) String password, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (username, password);
		Authentication auth = am.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
		
		System.out.println(new AuthenticationToken(username, auth.getAuthorities(), session.getId()));
		
		return new AuthenticationToken(username, auth.getAuthorities(), session.getId());
	}
	
	@GetMapping("/logout")
	public Object Logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("ResultCode", "OK");		
		return map;
	}
	
}