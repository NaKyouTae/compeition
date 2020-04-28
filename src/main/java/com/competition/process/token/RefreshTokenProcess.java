package com.competition.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.repository.token.RefreshTokenRepository;

@Component
@SuppressWarnings("unchecked")
public class RefreshTokenProcess {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	public <T extends Object> T isRefreshToken(String token) throws Exception {
		Boolean result = Boolean.FALSE;
		
		try {
			RefreshToken refresh = refreshTokenRepository.findByToken(token);
			
			if(refresh != null) {
				result = Boolean.TRUE;
			}else if(refresh == null) {
				result = Boolean.FALSE;
			}
			
			return (T) result;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	
	
}
