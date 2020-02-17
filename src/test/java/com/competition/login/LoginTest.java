package com.competition.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.competition.user.AuthenticationToken;


@SpringBootTest
public class LoginTest {
	@Autowired
	RestTemplate restTemplate;
	
	@Test
	void Login() {
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap();
		map.add("username", "test");
		map.add("pw", "test");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/json");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<AuthenticationToken> rs = restTemplate.postForEntity("/login", entity, AuthenticationToken.class);
		
		System.out.println(rs.toString());
	}
}
