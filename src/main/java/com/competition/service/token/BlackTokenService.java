package com.competition.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.process.token.BlackTokenProcess;

@Service
@SuppressWarnings("unchecked")
public class BlackTokenService {
	
	@Autowired
	private BlackTokenProcess blackTokenProcess; 
	
	public <T extends Object> T isBlackToken(String token) throws Exception {
		return (T) blackTokenProcess.isBlackToken(token);
	}
}
