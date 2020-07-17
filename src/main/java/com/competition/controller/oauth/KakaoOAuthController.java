package com.competition.controller.oauth;

import java.net.URI;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.history.HistoryLogin;
import com.competition.jpa.model.token.TokenRefresh;
import com.competition.jpa.model.user.User;
import com.competition.jpa.repository.system.config.SystemConfigRepository;
import com.competition.service.history.LoginHistoryService;
import com.competition.service.oauth.KakaoOAuthService;
import com.competition.service.oauth.OauthService;
import com.competition.service.token.JwtService;
import com.competition.service.token.refresh.RefreshTokenService;
import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;
import com.competition.util.DateUtil;
import com.competition.util.UUIDUtil;
import com.competition.vo.kakao.KakaoUserVO;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/oauth")
public class KakaoOAuthController {
	
	@Autowired
	private KakaoOAuthService kakaoOAuthService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private LoginHistoryService loginHistoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtUtill;
	
	@Autowired
	private OauthService oauthService;
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@GetMapping("/kakao")
	public <T extends Object> T loinByKakao(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<>();
		
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "authorization_code");
			map.add("client_id", "c4d7328a864db7fd90be93def8e00940");
			map.add("redirect_uri", "http://localhost:8090/oauth/kakao");
			map.add("code", code);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			
			ResponseEntity<Map> rs = rest.postForEntity("https://kauth.kakao.com/oauth/token", entity, Map.class);
			
			
			String Access = rs.getBody().get("access_token").toString();
			String Refresh = rs.getBody().get("refresh_token").toString();
			
			System.out.println(rs.toString());
			Cookie accessCookie = new Cookie("AWT", Access);
			accessCookie.setPath("http://127.0.0.1:4300");
			Cookie refreshCookie = new Cookie("RWT", Refresh);
			refreshCookie.setPath("http://127.0.0.1:4300");
			response.addCookie(accessCookie);
			response.addCookie(refreshCookie);
			
			URI redirect = new URI("http://127.0.0.1:4300");
			HttpHeaders reHeaders = new HttpHeaders();
			reHeaders.setLocation(redirect);
			reHeaders.add("Set-Cookie", "AWT=" + Access);
			reHeaders.add("Set-Cookie", "RWT=" + Refresh);
			
			KakaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(Access);
			
			
			Boolean userCheck = userService.checkUserName(kUser.getProperties().getNickname());
			
			if(!userCheck){
				// 회원 가입 프로세스			
				kakaoOAuthService.kakaoSignUp(kUser);
			}
			
			CustomUserDetails custom = (CustomUserDetails) userService.loadUserByUsername(kUser.getProperties().getNickname());
			Long exp = Long.parseLong(systemConfigRepository.findByConfigName("UWT_EXPRIATION").getConfigValue());
			
			String userJWT = jwtUtill.createUserToken(request, response, custom, new Date(System.currentTimeMillis() + exp));
			
			reHeaders.add("Set-Cookie", "UWT=" + userJWT);
			Cookie userCookie = new Cookie("UWT", userJWT);
			userCookie.setPath("http://127.0.0.1:4300");
			response.addCookie(userCookie);
			
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
			
			return (T) new ResponseEntity<>(reHeaders, HttpStatus.SEE_OTHER);
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
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
