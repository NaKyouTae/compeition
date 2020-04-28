package com.competition.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.repository.token.RefreshTokenRepository;

@Component
@SuppressWarnings("unchecked")
public class RefreshTokenProcess {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	public <T extends Object> T isRefreshToken(String token) throws Exception {
		return (T) refreshTokenRepository.findByToken(token);
	}
	
	
	
}
