package com.competition.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.competition.jpa.model.token.BlackToken;
import com.competition.jpa.model.token.RefreshToken;
import com.competition.service.token.BlackTokenService;
import com.competition.service.token.JwtService;
import com.competition.service.token.RefreshTokenService;


@SuppressWarnings("unchecked")
public class JwtInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private JwtService jwtService;
	@Autowired
	private BlackTokenService blackTokenService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

		String Access = request.getHeader("Access-JWT");
		String Refresh = request.getHeader("Refresh-JWT");
		
		// login이 되었을 경우
		if(Access != null && Refresh != null) {
				
			RefreshToken isRefresh = refreshTokenService.isRefreshToken(Refresh);
			BlackToken isBlack = blackTokenService.isBlackToken(Refresh);
			
			if(isRefresh == null && isBlack != null) return false;
			
			if(jwtService.validateToken(Access, "Access") && jwtService.validateToken(Refresh, "Refresh")) return true;
		}
		
		return true;
	}
}
