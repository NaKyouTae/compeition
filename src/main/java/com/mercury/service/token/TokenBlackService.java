package com.mercury.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.token.TokenBlack;
import com.mercury.process.token.TokenBlackProcess;

@Service
@SuppressWarnings("unchecked")
public class TokenBlackService {
	
	@Autowired
	private TokenBlackProcess blackTokenProcess; 
	
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
	public <T extends Object> T inBlackToken(TokenBlack token) throws Exception{
		try {
			return (T) blackTokenProcess.inBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upBlackToken(TokenBlack token) throws Exception{
		try {
			return (T) blackTokenProcess.upBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deBlackToken(TokenBlack token) throws Exception{
		try {
			return (T) blackTokenProcess.deBlackToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
