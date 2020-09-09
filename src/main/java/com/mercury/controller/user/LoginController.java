package com.mercury.controller.user;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.history.HistoryLogin;
import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;
import com.mercury.service.history.HistoryLoginService;
import com.mercury.service.token.JwtService;
import com.mercury.service.token.TokenRefreshService;
import com.mercury.service.user.UserService;
import com.mercury.user.CustomUserDetails;
import com.mercury.util.DateUtil;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private UserService	userService;
	@Autowired
	private HistoryLoginService loginHistoryService;
	@Autowired
	private JwtService jwtUtill;
	@Autowired
	private TokenRefreshService refreshTokenService;
	
	@Autowired
	private AuthenticationManager am;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
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
		
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4300");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		
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
				
				// RefreshToken, Access Token, User Token 생성
				{
					
					Long r_exp = Long.parseLong(systemConfigRepository.findByConfigName("RWT_EXPRIATION").getConfigValue());
					Long a_exp = Long.parseLong(systemConfigRepository.findByConfigName("AWT_EXPRIATION").getConfigValue());
					Long u_exp = Long.parseLong(systemConfigRepository.findByConfigName("UWT_EXPRIATION").getConfigValue());
					
					// Refresh Token, Access Token 생성
					String refreshJWT = jwtUtill.createRefreshToken(custom.getUsername(), new Date(System.currentTimeMillis() + r_exp));
					String accessJWT = jwtUtill.createAccessToken(custom.getUsername(), new Date(System.currentTimeMillis() + a_exp));
					String userJWT = jwtUtill.createUserToken(custom, new Date(System.currentTimeMillis() + u_exp));
					
					// Refresh Token & Access Token Cookie에 입력
//					headers.add("AWT", accessJWT);
//					headers.add("RWT", refreshJWT);
//					headers.add("UWT", userJWT);
					
					ResponseCookie awtCookie = ResponseCookie.from("AWT", accessJWT).sameSite("None").build();
					
					headers.add("loginType", "default");
					
					response.addCookie(new Cookie("AWT", accessJWT));
					response.addCookie(new Cookie("RWT", refreshJWT));
					response.addCookie(new Cookie("UWT", userJWT));
					
					// Refresh Token DB에 입력
					TokenRefresh refreshToken = new TokenRefresh();
					refreshToken.setUserName(custom.getUsername());
					refreshToken.setToken(refreshJWT);
					refreshToken.setInsertDate(DateUtil.now());
					
					refreshTokenService.inRefreshToken(refreshToken);
				}

				// 로그인 이력 DB에 입력
				{
					HistoryLogin his = new HistoryLogin();
	
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
			
			String awt = request.getHeader("AWT");
			String rwt = request.getHeader("RWT");
			String uwt = request.getHeader("UWT");
			
			res.setResultCode(HttpStatus.OK);
			res.setMessage("LogOut Success :)");
			res.setResult(refreshTokenService.deRefreshToken(refreshTokenService.seRefreshToken(rwt)));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}

		return res;
	}
	
}