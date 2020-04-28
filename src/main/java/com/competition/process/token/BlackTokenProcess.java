package com.competition.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.token.BlackToken;
import com.competition.jpa.repository.token.BlackTokenRepository;

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
}
