package com.competition.service.oauth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("unchecked")
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
	
	
	/**
	 * 
	 * Kakao Logout API Call
	 * 
	 * @param <T>
	 * @param acess
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T kakaoLogOut(String acess) throws Exception {
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Authorization", "Bearer " + acess);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			
			Object rs = rest.postForEntity("https://kapi.kakao.com/v1/user/logout", entity, Object.class);
			return (T) rs;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
	/**
	 * Kakao User Info API Call
	 * 
	 * @param <T>
	 * @param acess
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T getKakaoUserInfo(String acess) throws Exception {
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Authorization", "Bearer " + acess);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			
			Object rs = rest.postForEntity("https://kapi.kakao.com/v1/user/logout", entity, Object.class);
			
			return (T) rs;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
}
