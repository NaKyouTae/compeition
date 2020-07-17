package com.mercury.controller.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.service.token.JwtService;
import com.mercury.service.token.TokenRefreshService;
import com.mercury.service.user.UserService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/refreshs")
public class TokenRefreshController {
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenRefreshService refreshTokenService;
	
//	@GetMapping("")
//	public <T extends Object> T createToken(String refreshToken, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ControllerResponse<Object> res = new ControllerResponse<Object>();
//		try {
//			
//			RefreshToken tokenInfo = refreshTokenService.seRefreshToken(refreshToken);
//			
//			CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(tokenInfo.getUserName());
//			
//			String token = jwtService.createAccessToken(request, response, user, new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30)));
//			
//			res.setMessage("Success Create Token");
//			res.setResult(HttpStatus.OK);
//			res.setResult(token);
//		} catch (Exception e) {
//			res.setMessage(e.getMessage());
//			res.setResult(HttpStatus.INTERNAL_SERVER_ERROR);
//			res.setResult(e);
//		}
//		return (T) res;
//	}
	
	@GetMapping("/{username}")
	public <T extends Object> T seRefreshTokenByUsername(@RequestParam String username) throws Exception{
		ControllerResponse<List<TokenRefresh>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Refresh Tokens by User Name :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(refreshTokenService.seRefreshTokenByUsername(username));
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("")
	public <T extends Object> T seRefreshTokens() throws Exception{
		ControllerResponse<List<TokenRefresh>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Refresh Tokens :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(refreshTokenService.seRefreshTokens());
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{token}")
	public <T extends Object> T seRefreshToken(String token) throws Exception{
		ControllerResponse<List<TokenRefresh>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Refresh Token :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(refreshTokenService.seRefreshToken(token));
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	@PostMapping("")
	public <T extends Object> T inRefreshToken(TokenRefresh token) throws Exception{
		ControllerResponse<List<TokenRefresh>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Insert Refresh Token :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(refreshTokenService.inRefreshToken(token));
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	@PutMapping("/{token}")
	public <T extends Object> T upRefreshToken(TokenRefresh token) throws Exception{
		ControllerResponse<List<TokenRefresh>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Update Refresh Token :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(refreshTokenService.upRefreshToken(token));
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	@DeleteMapping("/{token}")
	public <T extends Object> T deRefreshToken(TokenRefresh token) throws Exception{
		ControllerResponse<List<TokenRefresh>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Delete Refresh Token :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(refreshTokenService.deRefreshToken(token));
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}

}
