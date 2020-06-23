package com.competition.service.oauth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoOAuthService {
	
	public void getAuthCode() throws Exception{
		try {
			RestTemplate rest = new RestTemplate();
			
			String uri = "https://kauth.kakao.com/oauth/authorize?client_id=c4d7328a864db7fd90be93def8e00940&redirect_uri=http://localhost:8090/oauth/kakao&response_type=code";
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);
			
			ResponseEntity<String> rs = rest.exchange(uri, HttpMethod.GET, entity, String.class);
			System.out.println(rs.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
