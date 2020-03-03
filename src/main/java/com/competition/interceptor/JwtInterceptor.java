package com.competition.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.competition.token.service.JwtService;


public class JwtInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) {
		
		String token = request.getHeader("Authentication");
		
		if(token != null) {
				
			ArrayList<GrantedAuthority> roles = (ArrayList<GrantedAuthority>) jwtService.getAuthentication(token).getAuthorities();
			
			GrantedAuthority auth_user = new SimpleGrantedAuthority("ROLE_USER");
			
			if(jwtService.validateToken(token) && roles.indexOf(auth_user) >= 0) {
				return true;
			}
		}
		
		return false;
	}
}
