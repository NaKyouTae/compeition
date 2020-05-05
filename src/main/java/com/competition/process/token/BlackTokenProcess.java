package com.competition.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.token.BlackToken;
import com.competition.jpa.repository.token.black.BlackTokenRepository;

@Component
@SuppressWarnings("unchecked")
public class BlackTokenProcess {
	
	@Autowired
	private BlackTokenRepository blackTokenRepository;
	
	public <T extends Object> T isBlackToken(String token) throws Exception {
		Boolean result = Boolean.TRUE;
		try {
			
			BlackToken black = blackTokenRepository.findByToken(token);
			
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
	public <T extends Object> T inBlackToken(BlackToken token) throws Exception {
		try {
			return (T) blackTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upBlackToken(BlackToken token) throws Exception {
		try {
			return (T) blackTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deBlackToken(BlackToken token) throws Exception {
		try {
			
			blackTokenRepository.delete(token);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
}
