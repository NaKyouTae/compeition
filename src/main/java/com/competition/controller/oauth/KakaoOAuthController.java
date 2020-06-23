package com.competition.controller.oauth;

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

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/oauth")
public class KakaoOAuthController {
	
	@GetMapping("/kakao")
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
}
