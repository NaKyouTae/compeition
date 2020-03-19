package com.competition.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.competition.user.AuthenticationToken;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {
	
	@Test
	void Login() {
		
		RestTemplate rest = new RestTemplate();
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap();
		map.add("username", "test");
		map.add("password", "test");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/json");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		
		ResponseEntity<AuthenticationToken> rs = rest.postForEntity("http://localhost:8080/user/login", entity, AuthenticationToken.class);
		
		System.out.println(rs.toString());
	}
}
