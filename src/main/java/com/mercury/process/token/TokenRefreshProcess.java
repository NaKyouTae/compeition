package com.mercury.process.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.jpa.repository.token.TokenRefreshRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class TokenRefreshProcess {

	@Autowired
	private TokenRefreshRepository refreshTokenRepository;

	public <T extends Object> T isRefreshToken(String token) throws Exception {
		Boolean result = Boolean.FALSE;

		TokenRefresh refresh = refreshTokenRepository.findByToken(token);

		if (refresh != null) {
			result = Boolean.TRUE;
		} else if (refresh == null) {
			result = Boolean.FALSE;
		}

		return (T) result;
	}

	public <T extends Object> T seRefreshTokenByUsername(String username)
			throws Exception {
		return (T) refreshTokenRepository.findByUserName(username);
	}
	public <T extends Object> T seRefreshTokens() throws Exception {
		return (T) refreshTokenRepository.findAll();
	}
	public <T extends Object> T seRefreshToken(String token) throws Exception {
		return (T) refreshTokenRepository.findByToken(token);
	}
	public <T extends Object> T inRefreshToken(TokenRefresh token)
			throws Exception {
		return (T) refreshTokenRepository.save(token);
	}
	public <T extends Object> T upRefreshToken(TokenRefresh token)
			throws Exception {
		return (T) refreshTokenRepository.save(token);
	}
	public <T extends Object> T deRefreshToken(TokenRefresh token)
			throws Exception {
		refreshTokenRepository.delete(token);
		return (T) Boolean.TRUE;
	}
	public <T extends Object> T deRefreshTokenAllEntities(
			List<TokenRefresh> tokens) throws Exception {
		refreshTokenRepository.deleteAll(tokens);
		return (T) Boolean.TRUE;
	}
}
