package com.competition.three;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThreeTest {

	@Test
	public void test() throws URISyntaxException {
		
		URI uri = new URI("http://localhost:8080/service/three/lists");
		
		RestTemplate rest = new RestTemplate();
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		
		headers.add("Content-Type", "application/json");
		
		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), Object.class);
		
		System.out.println(re.toString());
		
	}
}
