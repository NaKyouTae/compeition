package com.competition.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.repository.token.refresh.RefreshTokenRepository;

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
	
	public <T extends Object> T seRefreshTokens() throws Exception {
		try {
			return (T) refreshTokenRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seRefreshToken(String token) throws Exception {
		try {
			return (T) refreshTokenRepository.findByToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inRefreshToken(RefreshToken token) throws Exception {
		try {
			return (T) refreshTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upRefreshToken(RefreshToken token) throws Exception {
		try {
			return (T) refreshTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deRefreshToken(RefreshToken token) throws Exception {
		try {
			
			refreshTokenRepository.delete(token);
			
			return (T) Boolean.TRUE; 
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	
	
}
