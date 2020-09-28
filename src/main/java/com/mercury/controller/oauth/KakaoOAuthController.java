package com.mercury.controller.oauth;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.history.HistoryLogin;
import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;
import com.mercury.service.history.HistoryLoginService;
import com.mercury.service.oauth.KakaoOAuthService;
import com.mercury.service.token.JwtService;
import com.mercury.service.token.TokenRefreshService;
import com.mercury.service.user.UserService;
import com.mercury.user.CustomUserDetails;
import com.mercury.util.CookieUtil;
import com.mercury.util.DateUtil;
import com.mercury.util.UUIDUtil;
import com.mercury.vo.kakao.KakaoUserVO;

import io.jsonwebtoken.Claims;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/user")
public class KakaoOAuthController {
	
	@Autowired
	private KakaoOAuthService kakaoOAuthService;
	
	@Autowired
	private TokenRefreshService refreshTokenService;
	
	@Autowired
	private HistoryLoginService loginHistoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtUtill;
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@GetMapping("/kakao")
	public <T extends Object> T loinByKakao(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<>();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "authorization_code");
			map.add("client_id", "c4d7328a864db7fd90be93def8e00940");
			map.add("redirect_uri", "http://localhost:4300/user/kakao");
			map.add("code", code);
			
			headers.add("Context-type", "application/json");
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			
			ResponseEntity<Map> rs = rest.postForEntity("https://kauth.kakao.com/oauth/token", entity, Map.class);
			
			String Access = rs.getBody().get("access_token").toString();
			String Refresh = rs.getBody().get("refresh_token").toString();
			
			KakaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(Access);
			
			Boolean userCheck = userService.checkUserName(kUser.getProperties().getNickname());
			
			if(!userCheck){
				// 회원 가입 프로세스
				kakaoOAuthService.kakaoSignUp(kUser);
			}
			
			CustomUserDetails custom = (CustomUserDetails) userService.loadUserByUsername(kUser.getProperties().getNickname());
			Long exp = Long.parseLong(systemConfigRepository.findByConfigName("UWT_EXPRIATION").getConfigValue());
			
			String userJWT = jwtUtill.createUserToken(custom, new Date(System.currentTimeMillis() + exp));
			
			
			{
				// Refresh Token DB에 입력
				TokenRefresh refreshToken = new TokenRefresh();
				refreshToken.setUserName(kUser.getKakao_account().getProfile().getNickname());
				refreshToken.setToken(Refresh);
				refreshToken.setInsertDate(DateUtil.now());
				
				refreshTokenService.inRefreshToken(refreshToken);
			}
			
			// 로그인 이력 DB에 입력
			{
				HistoryLogin his = new HistoryLogin();
				
				his.setIdx(UUIDUtil.randomString());
				his.setUserIdx(kUser.getId());
				his.setUserName(kUser.getKakao_account().getProfile().getNickname());
				his.setAccessDate(DateUtil.now());
				his.setBrowser(request.getHeader("User-Agent"));
				
				loginHistoryService.inLoginHistory(his);
			}
			
			// Cookie, Response			
			Map<String, Object> accessInfo = kakaoOAuthService.getAccessInfo(Access);
			Claims u_body = jwtUtill.getUserPayLoad(userJWT);
			
			Cookie accessCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("AWT").value(Access).maxAge((Integer) accessInfo.get("expires_in")).build().getCookie();
			Cookie refreshCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("RWT").value(Refresh).maxAge(((Integer) accessInfo.get("expires_in") * 7)).build().getCookie();
			Cookie userCookie 		= new CookieUtil.Builder().domain("localhost").path("/").name("UWT").value(userJWT).maxAge((int) u_body.getExpiration().getTime()).build().getCookie();
			Cookie loginTypeCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("loginType").value("kakao").maxAge((int) u_body.getExpiration().getTime()).build().getCookie();

			response.addCookie(accessCookie);
			response.addCookie(refreshCookie);
			response.addCookie(userCookie);
			response.addCookie(loginTypeCookie);
			
			response.sendRedirect("http://localhost:4300");
			
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Kakao Login :) ");
			res.setResult(null);
		
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/kakao/logout")
	public <T extends Object> T looutByKakao(@RequestParam String acess, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Log Out by Kakao :) ");
			res.setResult(kakaoOAuthService.kakaoLogOut(acess));

			Cookie accessCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("AWT").value(null).maxAge(0).build().getCookie();
			Cookie refreshCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("RWT").value(null).maxAge(0).build().getCookie();
			Cookie userCookie 		= new CookieUtil.Builder().domain("localhost").path("/").name("UWT").value(null).maxAge(0).build().getCookie();
			Cookie loginTypeCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("loginType").value(null).maxAge(0).build().getCookie();

			response.addCookie(accessCookie);
			response.addCookie(refreshCookie);
			response.addCookie(userCookie);
			response.addCookie(loginTypeCookie);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@DeleteMapping("/kakao/withdrawal")
	public <T extends Object> T withdrawal(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();

		ObjectMapper om = new ObjectMapper();
		User user = om.convertValue(map.get("user"), User.class);		
		
		String access = (String) map.get("access");
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Withdrawal by Kakao :) ");
			res.setResult(kakaoOAuthService.kakaoWithdrawal(user, access));
			
			Cookie accessCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("AWT").value(null).maxAge(0).build().getCookie();
			Cookie refreshCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("RWT").value(null).maxAge(0).build().getCookie();
			Cookie userCookie 		= new CookieUtil.Builder().domain("localhost").path("/").name("UWT").value(null).maxAge(0).build().getCookie();
			Cookie loginTypeCookie 	= new CookieUtil.Builder().domain("localhost").path("/").name("loginType").value(null).maxAge(0).build().getCookie();

			response.addCookie(accessCookie);
			response.addCookie(refreshCookie);
			response.addCookie(userCookie);
			response.addCookie(loginTypeCookie);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
}
