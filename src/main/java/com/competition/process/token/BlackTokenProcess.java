package com.competition.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.repository.token.BlackTokenRepository;

@Component
@SuppressWarnings("unchecked")
public class BlackTokenProcess {
	
	@Autowired
	private BlackTokenRepository blackTokenRepository;
	
	public <T extends Object> T isBlackToken(String token) throws Exception {
		return (T) blackTokenRepository.findByToken(token);
	}
	
	
	
}
