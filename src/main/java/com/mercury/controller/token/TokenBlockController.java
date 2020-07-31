package com.mercury.controller.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.token.TokenBlock;
import com.mercury.service.token.TokenBlockService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/blocks")
public class TokenBlockController {
	
	@Autowired
	private TokenBlockService blockTokenService;
	
	@GetMapping("")
	public <T extends Object> T seBlockTokens() throws Exception{
		ControllerResponse<List<TokenBlock>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Block Tokens :) ");
			res.setResult(blockTokenService.seBlockTokens());
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@GetMapping("/{token}")
	public <T extends Object> T seBlockToken(String token) throws Exception{
		ControllerResponse<TokenBlock> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Block Token :) ");
			res.setResult(blockTokenService.seBlockToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@PostMapping("")
	public <T extends Object> T inBlockToken(TokenBlock token) throws Exception{
		ControllerResponse<TokenBlock> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Insert Block Token :) ");
			res.setResult(blockTokenService.inBlockToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@PutMapping("/{token}")
	public <T extends Object> T upBlockToken(TokenBlock token) throws Exception{
		ControllerResponse<TokenBlock> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Update Block Token :) ");
			res.setResult(blockTokenService.upBlockToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@DeleteMapping("/{token}")
	public <T extends Object> T deBlockToken(TokenBlock token) throws Exception{
		ControllerResponse<TokenBlock> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Delete Block Token :) ");
			res.setResult(blockTokenService.deBlockToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}

}
