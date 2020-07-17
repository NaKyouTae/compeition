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
import com.mercury.jpa.model.token.TokenBlack;
import com.mercury.service.token.TokenBlackService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/blacks")
public class TokenBlackController {
	
	@Autowired
	private TokenBlackService blackTokenService;
	
	@GetMapping("")
	public <T extends Object> T seBlackTokens() throws Exception{
		ControllerResponse<List<TokenBlack>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Black Tokens :) ");
			res.setResult(blackTokenService.seBlackTokens());
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@GetMapping("/{token}")
	public <T extends Object> T seBlackToken(String token) throws Exception{
		ControllerResponse<TokenBlack> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Black Token :) ");
			res.setResult(blackTokenService.seBlackToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@PostMapping("")
	public <T extends Object> T inBlackToken(TokenBlack token) throws Exception{
		ControllerResponse<TokenBlack> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Insert Black Token :) ");
			res.setResult(blackTokenService.inBlackToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@PutMapping("/{token}")
	public <T extends Object> T upBlackToken(TokenBlack token) throws Exception{
		ControllerResponse<TokenBlack> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Update Black Token :) ");
			res.setResult(blackTokenService.upBlackToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}
	@DeleteMapping("/{token}")
	public <T extends Object> T deBlackToken(TokenBlack token) throws Exception{
		ControllerResponse<TokenBlack> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Delete Black Token :) ");
			res.setResult(blackTokenService.deBlackToken(token));
			res.setResultCode(HttpStatus.OK);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(null);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return (T) res;
	}

}
