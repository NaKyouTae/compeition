package com.competition.controller.token.black;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.repository.token.RefreshTokenRepository;
import com.competition.service.token.JwtService;
import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/tokens")
public class BlackTokenController {
	
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@GetMapping("")
	public <T extends Object> T createToken(String refreshToken, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<Object>();
		try {
			
			RefreshToken tokenInfo = refreshTokenRepository.findByToken(refreshToken);
			
			CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(tokenInfo.getUserName());
			
			String token = jwtService.createAccessToken(request, response, user, new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30)));
			
			res.setMessage("Success Create Token");
			res.setResult(HttpStatus.OK);
			res.setResult(token);
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setResult(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(e);
		}
		return (T) res;
	}

}
