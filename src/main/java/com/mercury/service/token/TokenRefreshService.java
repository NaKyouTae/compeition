package com.mercury.service.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.process.token.TokenRefreshProcess;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TokenRefreshService {

	@Autowired
	private TokenRefreshProcess refreshTokenProcess;

	public <T extends Object> T isRefreshToken(String token) throws Exception {
		return (T) refreshTokenProcess.isRefreshToken(token);
	}

	public <T extends Object> T seRefreshTokenByUsername(String username)
			throws Exception {
		return refreshTokenProcess.seRefreshTokenByUsername(username);
	}
	public <T extends Object> T seRefreshTokens() throws Exception {
		return refreshTokenProcess.seRefreshTokens();
	}
	public <T extends Object> T seRefreshToken(String token) throws Exception {
		return refreshTokenProcess.seRefreshToken(token);
	}
	public <T extends Object> T inRefreshToken(TokenRefresh token)
			throws Exception {
		return refreshTokenProcess.inRefreshToken(token);
	}
	public <T extends Object> T upRefreshToken(TokenRefresh token)
			throws Exception {
		return refreshTokenProcess.upRefreshToken(token);
	}
	public <T extends Object> T deRefreshToken(TokenRefresh token)
			throws Exception {
		return refreshTokenProcess.deRefreshToken(token);
	}
	public <T extends Object> T deRefreshTokenAllEntities(
			List<TokenRefresh> tokens) throws Exception {
		return refreshTokenProcess.deRefreshTokenAllEntities(tokens);
	}
}
