package com.mercury.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.token.TokenBlack;
import com.mercury.jpa.repository.token.TokenBlackRepository;

@Component
@SuppressWarnings("unchecked")
public class TokenBlackProcess {
	
	@Autowired
	private TokenBlackRepository blackTokenRepository;
	
	public <T extends Object> T isBlackToken(String token) throws Exception {
		Boolean result = Boolean.TRUE;
		try {
			
			TokenBlack black = blackTokenRepository.findByToken(token);
			
			if(black != null) {
				result = Boolean.TRUE;
			}else if(black == null) {
				result = Boolean.FALSE;
			}
			
			return (T) result;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seBlackTokens() throws Exception {
		try {
			return (T) blackTokenRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seBlackToken(String token) throws Exception {
		try {
			return (T) blackTokenRepository.findByToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inBlackToken(TokenBlack token) throws Exception {
		try {
			return (T) blackTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upBlackToken(TokenBlack token) throws Exception {
		try {
			return (T) blackTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deBlackToken(TokenBlack token) throws Exception {
		try {
			
			blackTokenRepository.delete(token);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
}
