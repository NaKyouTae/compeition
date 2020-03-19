package com.competition.weekword;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeekWordTest {

	@Test
	public void test() throws URISyntaxException {
		
		URI uri = new URI("http://localhost:8080/service/weekword/lists");
		
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<Object> re = rest.getForEntity(uri, Object.class);
		
		System.out.println(re.toString());
		
	}
}
