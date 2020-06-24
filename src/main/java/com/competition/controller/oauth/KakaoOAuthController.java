package com.competition.controller.oauth;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.competition.common.ControllerResponse;
import com.competition.enums.SNSEnum;
import com.competition.jpa.model.history.LoginHistory;
import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.model.user.User;
import com.competition.service.history.LoginHistoryService;
import com.competition.service.oauth.KakaoOAuthService;
import com.competition.service.token.refresh.RefreshTokenService;
import com.competition.service.user.UserService;
import com.competition.util.DateUtil;
import com.competition.vo.kakao.KaKaoUserVO;

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
			Cookie accessCookie = new Cookie("Access-JWT", Access);
			accessCookie.setPath("/");
			Cookie refreshCookie = new Cookie("Refresh-JWT", Refresh);
			refreshCookie.setPath("/");
			response.addCookie(accessCookie);
			response.addCookie(refreshCookie);
			
			URI redirect = new URI("http://127.0.0.1:4300");
			HttpHeaders reHeaders = new HttpHeaders();
			reHeaders.setLocation(redirect);
			reHeaders.add("Set-Cookie", "Access-JWT=" + Access);
			reHeaders.add("Set-Cookie", "Refresh-JWT=" + Refresh);
			
			KaKaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(Access);
			
			
			User user = userService.seUserByIdx(kUser.getId());
			
			if(user == null){
			
				// 회원 가입 프로세스 필요
				// Sign Kakao User				
				User SKU = new User();
				
				SKU.setIdx(kUser.getId());
				if(kUser.getKakao_account().getEmail_needs_agreement()) {					
					SKU.setEmail(kUser.getKakao_account().getEmail());
				}
				SKU.setSns(SNSEnum.KAKAO);
				
				userService.signUp(SKU);
			}
			
			{
				// Refresh Token DB에 입력
				RefreshToken refreshToken = new RefreshToken();
				refreshToken.setUserName(kUser.getKakao_account().getProfile().getNickname());
				refreshToken.setToken(Refresh);
				refreshToken.setInsertDate(DateUtil.now());
				
				refreshTokenService.inRefreshToken(refreshToken);
			}
			
			// 로그인 이력 DB에 입력
			{
				LoginHistory his = new LoginHistory();
				
				his.setIdx(UUID.randomUUID().toString().replace("-", ""));
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
			session.removeAttribute("Access-JWT");
			session.removeAttribute("Refresh-JWT");
			
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
}
