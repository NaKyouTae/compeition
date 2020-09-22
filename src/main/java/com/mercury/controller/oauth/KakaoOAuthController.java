package com.mercury.controller.oauth;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.mercury.util.DateUtil;
import com.mercury.util.UUIDUtil;
import com.mercury.vo.kakao.KakaoUserVO;

import io.jsonwebtoken.Claims;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/oauth")
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
	public void loinByKakao(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "authorization_code");
			map.add("client_id", "c4d7328a864db7fd90be93def8e00940");
			map.add("redirect_uri", "http://localhost:4300/oauth/kakao");
			map.add("code", code);
			
			HttpHeaders headers = new HttpHeaders();
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
			{
				// Access Token Cookie Create
				Cookie AC = new Cookie("AWT", Access);
				Map<String, Object> accessInfo = kakaoOAuthService.getAccessInfo(Access);
				AC.setMaxAge((Integer) accessInfo.get("expires_in"));
				AC.setDomain(request.getRemoteHost());
				AC.setPath("/");
				
				// Refresh Token Cookie Create
				Cookie RC = new Cookie("RWT", Refresh);
				Map<String, Object> refreshInfo = kakaoOAuthService.getAccessInfo(Refresh);
				RC.setMaxAge((Integer) refreshInfo.get("expires_in"));
				RC.setDomain(request.getRemoteHost());
				RC.setPath("/");
				
				// User Token Cookie Create			
				Cookie UC = new Cookie("UWT", userJWT);
				Claims u_body = jwtUtill.getUserPayLoad(userJWT);
				int uMax = (int) u_body.getExpiration().getTime();
				UC.setMaxAge(uMax);
				UC.setDomain(request.getRemoteHost());
				UC.setPath("/");
				
				// Login Tyep Cookie Create			
				Cookie lt = new Cookie("loginType", "kakao");
				lt.setMaxAge(uMax);
				lt.setDomain(request.getRemoteHost());
				lt.setPath("/");
				
				response.addCookie(lt);
				response.addCookie(AC);
				response.addCookie(RC);
				response.addCookie(UC);
				response.sendRedirect("http://localhost:4300");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/kakao/logout")
	public <T extends Object> T looutByKakao(@RequestParam String acess, HttpSession session) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<>();
		try {
			Object rs = kakaoOAuthService.kakaoLogOut(acess);
			session.removeAttribute("AWT");
			session.removeAttribute("RWT");
			session.removeAttribute("UWT");
			
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Log Out by Kakao :) ");
			res.setResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@DeleteMapping("/kakao/withdrawal")
	public <T extends Object> T withdrawal(@RequestBody User user) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Withdrawal by Kakao :) ");
			res.setResult(kakaoOAuthService.kakaoWithdrawal(user));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
}
