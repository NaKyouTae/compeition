package com.competition.controller.token.black;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.token.BlackToken;
import com.competition.service.token.black.BlackTokenService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/blacks")
public class BlackTokenController {
	
	@Autowired
	private BlackTokenService blackTokenService;
	
	@GetMapping("")
	public <T extends Object> T seBlackTokens() throws Exception{
		ControllerResponse<List<BlackToken>> res = new ControllerResponse<>();
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
		ControllerResponse<BlackToken> res = new ControllerResponse<>();
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
	public <T extends Object> T inBlackToken(BlackToken token) throws Exception{
		ControllerResponse<BlackToken> res = new ControllerResponse<>();
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
	public <T extends Object> T upBlackToken(BlackToken token) throws Exception{
		ControllerResponse<BlackToken> res = new ControllerResponse<>();
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
	public <T extends Object> T deBlackToken(BlackToken token) throws Exception{
		ControllerResponse<BlackToken> res = new ControllerResponse<>();
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
