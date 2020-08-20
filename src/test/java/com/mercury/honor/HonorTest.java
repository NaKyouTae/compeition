package com.mercury.honor;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * 2020-03-20 CRUD Completed
 * 
 * @author nkt
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HonorTest {

	@Test
	public void test() throws Exception {
		seHonor();
	}

	public void seHonor() throws Exception {
		URI uri = new URI("http://localhost:8090/service/honor");

		RestTemplate rest = new RestTemplate();

		ResponseEntity<Object> re = rest.getForEntity(uri, Object.class);

		System.out.println(re.getBody().toString());
	}

}
