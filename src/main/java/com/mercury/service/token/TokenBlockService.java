package com.mercury.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.token.TokenBlock;
import com.mercury.process.token.TokenBlockProcess;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TokenBlockService {

	@Autowired
	private TokenBlockProcess blockTokenProcess;

	public <T extends Object> T isBlockToken(String token) throws Exception {
		return (T) blockTokenProcess.isBlockToken(token);
	}

	public <T extends Object> T seBlockTokens() throws Exception {
		return (T) blockTokenProcess.seBlockTokens();
	}
	public <T extends Object> T seBlockToken(String token) throws Exception {
		return (T) blockTokenProcess.seBlockToken(token);
	}
	public <T extends Object> T inBlockToken(TokenBlock token)
			throws Exception {
		return (T) blockTokenProcess.inBlockToken(token);
	}
	public <T extends Object> T upBlockToken(TokenBlock token)
			throws Exception {
		return (T) blockTokenProcess.upBlockToken(token);
	}
	public <T extends Object> T deBlockToken(TokenBlock token)
			throws Exception {
		return (T) blockTokenProcess.deBlockToken(token);
	}
}
