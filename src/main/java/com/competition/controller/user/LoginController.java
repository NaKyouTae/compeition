package com.competition.controller.user;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.history.LoginHistory;
import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.model.user.User;
import com.competition.service.history.LoginHistoryService;
import com.competition.service.token.JwtService;
import com.competition.service.token.refresh.RefreshTokenService;
import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;
import com.competition.util.DateUtil;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private UserService	userService;
	
	@Autowired
	private LoginHistoryService loginHistoryService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtUtill;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@CrossOrigin("*")
	@PostMapping("/signup")
	public <T> ControllerResponse<Object> SignUp(@ModelAttribute(name = "signup") User user) throws Exception {
		ControllerResponse<Object> response = new ControllerResponse<Object>();

		try {
			user.setInsertDate(DateUtil.now());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			response.setResultCode(HttpStatus.OK);
			response.setMessage("Sing Up Success :)");
			response.setResult(userService.signUp(user));
		}catch(Exception e) {
			response.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setResult(null);
		}
		
		return response;
	}
	
	@CrossOrigin("*")
	@PostMapping("/login")
	public ResponseEntity<ControllerResponse<Boolean>> Login(@RequestBody Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String username = (String)map.get("username");
		String password = (String)map.get("password");
		
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			Authentication auth = am.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());
			CustomUserDetails custom =  (CustomUserDetails) auth.getPrincipal();		
			
			if(custom != null) {
				
				// RefreshToken, Access Token 생성 후 DB에 입력
				{
					// Refresh Token, Access Token 생성
					String accessJWT = jwtUtill.createAccessToken(request, response, custom, new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30)));
					String refreshJWT = jwtUtill.createRefreshToken(request, response, custom.getUsername(), new Date(System.currentTimeMillis() + 7 * (1000 * 60 * 60 * 24)));
					
					// Refresh Token & Access Token Header에 입력
					headers.add("Access-JWT", accessJWT);
					headers.add("Refresh-JWT", refreshJWT);
					
					// Refresh Token DB에 입력
					RefreshToken refreshToken = new RefreshToken();
					refreshToken.setUserName(custom.getUsername());
					refreshToken.setToken(refreshJWT);
					refreshToken.setInsertDate(DateUtil.now());
					
					refreshTokenService.inRefreshToken(refreshToken);
				}

				// 로그인 이력 DB에 입력
				{
					LoginHistory his = new LoginHistory();
	
					his.setIdx(UUID.randomUUID().toString().replace("-", ""));
					his.setUserIdx(custom.getUser().getIdx());
					his.setUserName(custom.getUser().getUsername());
					his.setAccessDate(DateUtil.now());
					his.setBrowser(request.getHeader("User-Agent"));
					
					loginHistoryService.inLoginHistory(his);
				}
			}
			
			res.setMessage("Success Login :)");
			res.setResultCode(HttpStatus.OK);
			res.setResult(Boolean.TRUE);
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return ResponseEntity.ok().headers(headers).body(res);
	}
	
	@GetMapping("/logout")
	public ControllerResponse<Object> Logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<Object>();
		
		try {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			
//			if (auth != null) {
//				new SecurityContextLogoutHandler().logout(request, response, auth);
//			}
			
			String refresh = request.getHeader("Refresh-JWT");
			
			res.setResultCode(HttpStatus.OK);
			res.setMessage("LogOut Success :)");
			res.setResult(refreshTokenService.deRefreshToken(refreshTokenService.seRefreshToken(refresh)));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}

		return res;
	}
	
}