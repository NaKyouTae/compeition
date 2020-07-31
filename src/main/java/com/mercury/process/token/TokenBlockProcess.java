package com.mercury.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.token.TokenBlock;
import com.mercury.jpa.repository.token.TokenBlockRepository;

@Component
@SuppressWarnings("unchecked")
public class TokenBlockProcess {
	
	@Autowired
	private TokenBlockRepository blockTokenRepository;
	
	public <T extends Object> T isBlockToken(String token) throws Exception {
		Boolean result = Boolean.TRUE;
		try {
			
			TokenBlock block = blockTokenRepository.findByToken(token);
			
			if(block != null) {
				result = Boolean.TRUE;
			}else if(block == null) {
				result = Boolean.FALSE;
			}
			
			return (T) result;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seBlockTokens() throws Exception {
		try {
			return (T) blockTokenRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seBlockToken(String token) throws Exception {
		try {
			return (T) blockTokenRepository.findByToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inBlockToken(TokenBlock token) throws Exception {
		try {
			return (T) blockTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upBlockToken(TokenBlock token) throws Exception {
		try {
			return (T) blockTokenRepository.save(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deBlockToken(TokenBlock token) throws Exception {
		try {
			
			blockTokenRepository.delete(token);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
}
