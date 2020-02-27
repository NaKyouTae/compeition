package com.competition.user.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.competition.common.ControllerResponse;
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
	public <T> ControllerResponse<Object> SignUp(@ModelAttribute(name = "signup") User user) throws Exception {
		ControllerResponse<Object> response = new ControllerResponse<Object>();

		try {
			user.setInsert_date(LocalDateTime.now());
			user.setPw(passwordEncoder.encode(user.getPw()));
			
			response.setResultCode(HttpStatus.OK);
			response.setMessage("Sing Up Success :)");
			response.setResult(userRepository.save(user));
		}catch(Exception e) {
			response.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setResult(null);
		}
		
		return response;
	}
	
	@CrossOrigin("*")
	@PostMapping("/login")
	public ControllerResponse<AuthenticationToken> Login(@RequestParam(name="username", required = true) String username, @RequestParam(name="password", required = true) String password, HttpSession session) throws Exception {
		ControllerResponse<AuthenticationToken> res = new ControllerResponse<AuthenticationToken>();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (username, password);
		
		try {
			Authentication auth = am.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());
			
			res.setMessage("Success Login :)");
			res.setResult(new AuthenticationToken(username, auth.getAuthorities(), session.getId()));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return res;
	}
	
	@GetMapping("/logout")
	public ControllerResponse<Object> Logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<Object>();
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			
			res.setResultCode(HttpStatus.OK);
			res.setMessage("LogOut Success :)");
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}

		return res;
	}
	
}