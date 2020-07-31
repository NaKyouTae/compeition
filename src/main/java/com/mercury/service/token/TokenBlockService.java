package com.mercury.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.token.TokenBlock;
import com.mercury.process.token.TokenBlockProcess;

@Service
@SuppressWarnings("unchecked")
public class TokenBlockService {
	
	@Autowired
	private TokenBlockProcess blockTokenProcess; 
	
	public <T extends Object> T isBlockToken(String token) throws Exception {
		return (T) blockTokenProcess.isBlockToken(token);
	}
	
	public <T extends Object> T seBlockTokens() throws Exception{
		try {
			return (T) blockTokenProcess.seBlockTokens();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seBlockToken(String token) throws Exception{
		try {
			return (T) blockTokenProcess.seBlockToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inBlockToken(TokenBlock token) throws Exception{
		try {
			return (T) blockTokenProcess.inBlockToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upBlockToken(TokenBlock token) throws Exception{
		try {
			return (T) blockTokenProcess.upBlockToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deBlockToken(TokenBlock token) throws Exception{
		try {
			return (T) blockTokenProcess.deBlockToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
