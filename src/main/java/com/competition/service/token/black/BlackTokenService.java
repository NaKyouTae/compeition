package com.competition.service.token.black;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.token.BlackToken;
import com.competition.process.token.BlackTokenProcess;

@Service
@SuppressWarnings("unchecked")
public class BlackTokenService {
	
	@Autowired
	private BlackTokenProcess blackTokenProcess; 
	
	public <T extends Object> T isBlackToken(String token) throws Exception {
		return (T) blackTokenProcess.isBlackToken(token);
	}
	
	public <T extends Object> T seBlackTokens() throws Exception{
		try {
			return (T) blackTokenProcess.seBlackTokens();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seBlackToken(String token) throws Exception{
		try {
			return (T) blackTokenProcess.seBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inBlackToken(BlackToken token) throws Exception{
		try {
			return (T) blackTokenProcess.inBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upBlackToken(BlackToken token) throws Exception{
		try {
			return (T) blackTokenProcess.upBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deBlackToken(BlackToken token) throws Exception{
		try {
			return (T) blackTokenProcess.deBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
