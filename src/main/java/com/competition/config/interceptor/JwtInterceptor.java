package com.competition.config.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.competition.service.token.BlackTokenService;
import com.competition.service.token.JwtService;
import com.competition.service.token.RefreshTokenService;
import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;

@SuppressWarnings("unchecked")
public class JwtInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private JwtService jwtService;
	@Autowired
	private JwtService jwtUtill;
	@Autowired
	private BlackTokenService blackTokenService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private UserService userSerivce;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

		String Access = request.getHeader("Access-JWT");
		String Refresh = request.getHeader("Refresh-JWT");
		
		// login이 되었을 경우
		if(Access != null && Refresh != null) {
			
			
			if(jwtService.validateToken(Refresh, "Refresh")) {
				Boolean isRefresh = refreshTokenService.isRefreshToken(Refresh);
				Boolean isBlack = blackTokenService.isBlackToken(Refresh);
				
				if(!isRefresh && isBlack) return false;
			}
			
			if(!jwtService.validateToken(Access, "Access")) {
				
				String username = jwtService.getUserInfo(Access, "Access");
				CustomUserDetails user = (CustomUserDetails) userSerivce.loadUserByUsername(username);
				
				response.setHeader("Access-JWT", jwtUtill.createAccessToken(request, response, user, new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30))));
			}
				
			
		}
		
		return true;
	}
}
