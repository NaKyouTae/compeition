package com.competition.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.process.token.RefreshTokenProcess;

@Service
@SuppressWarnings("unchecked")
public class RefreshTokenService {
	
	@Autowired
	private RefreshTokenProcess refreshTokenProcess; 
	
	public <T extends Object> T isRefreshToken(String token) throws Exception {
		return (T) refreshTokenProcess.isRefreshToken(token);
	}
}
