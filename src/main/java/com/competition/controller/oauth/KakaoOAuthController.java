package com.competition.controller.oauth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.competition.common.ControllerResponse;
import com.competition.service.oauth.KakaoOAuthService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/oauth/kakao")
public class KakaoOAuthController {
	
	@Autowired
	private KakaoOAuthService kakaoOAuthService;
	
	@GetMapping("")
	public <T extends Object> T loinByKakao(@RequestParam("code") String code) throws Exception {
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
			
			Object rs = rest.postForEntity("https://kauth.kakao.com/oauth/token", entity, Object.class);
			
			System.out.println(rs.toString());
			
			return (T) "redirect:localhost:4300/three";
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/logout")
	public <T extends Object> T looutByKakao(@RequestParam String acess, HttpSession session) throws Exception {
		ControllerResponse<Object> res = new ControllerResponse<>();
		try {
			Object rs = kakaoOAuthService.kakaoLogOut(acess);
			session.removeAttribute("access_Token");
			session.removeAttribute("userId");
			
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
