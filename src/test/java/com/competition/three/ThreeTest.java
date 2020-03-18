package com.competition.three;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ThreeTest {

	@Test
	public void test() {
		RestTemplate rest = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/json");
		
		ResponseEntity<Object> rs = rest.getForEntity("http://localhost:8080/service/three/lists", Object.class);
		
		System.out.println(rs.toString());
	}
}
