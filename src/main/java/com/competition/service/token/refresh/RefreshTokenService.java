package com.competition.service.token.refresh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.token.RefreshToken;
import com.competition.process.token.RefreshTokenProcess;

@Service
@SuppressWarnings("unchecked")
public class RefreshTokenService {
	
	@Autowired
	private RefreshTokenProcess refreshTokenProcess; 
	
	public <T extends Object> T isRefreshToken(String token) throws Exception {
		return (T) refreshTokenProcess.isRefreshToken(token);
	}
	
	public <T extends Object> T seRefreshTokenByUsername(String username) throws Exception {
		try {
			return refreshTokenProcess.seRefreshTokenByUsername(username);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seRefreshTokens() throws Exception {
		try {
			return refreshTokenProcess.seRefreshTokens();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T seRefreshToken(String token) throws Exception {
		try {
			return refreshTokenProcess.seRefreshToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inRefreshToken(RefreshToken token) throws Exception {
		try {
			return refreshTokenProcess.inRefreshToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upRefreshToken(RefreshToken token) throws Exception {
		try {
			return refreshTokenProcess.upRefreshToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deRefreshToken(RefreshToken token) throws Exception {
		try {
			return refreshTokenProcess.deRefreshToken(token);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deRefreshTokenAllEntities(List<RefreshToken> tokens) throws Exception {
		try {
			return refreshTokenProcess.deRefreshTokenAllEntities(tokens);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
