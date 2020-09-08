package com.mercury.process.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.token.TokenBlock;
import com.mercury.jpa.repository.token.TokenBlockRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class TokenBlockProcess {

	@Autowired
	private TokenBlockRepository blockTokenRepository;

	public <T extends Object> T isBlockToken(String token) throws Exception {
		Boolean result = Boolean.TRUE;

		TokenBlock block = blockTokenRepository.findByToken(token);

		if (block != null) {
			result = Boolean.TRUE;
		} else if (block == null) {
			result = Boolean.FALSE;
		}

		return (T) result;
	}

	public <T extends Object> T seBlockTokens() throws Exception {
		return (T) blockTokenRepository.findAll();
	}
	public <T extends Object> T seBlockToken(String token) throws Exception {
		return (T) blockTokenRepository.findByToken(token);
	}
	public <T extends Object> T inBlockToken(TokenBlock token)
			throws Exception {
		return (T) blockTokenRepository.save(token);
	}
	public <T extends Object> T upBlockToken(TokenBlock token)
			throws Exception {
		return (T) blockTokenRepository.save(token);
	}
	public <T extends Object> T deBlockToken(TokenBlock token)
			throws Exception {
		blockTokenRepository.delete(token);

		return (T) Boolean.TRUE;
	}
}
