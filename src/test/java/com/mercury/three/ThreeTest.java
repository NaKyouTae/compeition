package com.mercury.three;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.jpa.model.three.Three;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThreeTest {

	@Test
	public void test() throws Exception {
		seThree();
//		inThree();
//		upThree();
//		deThree();
	}

	public void seThree() throws Exception {
		URI uri = new URI("http://127.0.0.1:8080/service/three/lists");

		RestTemplate rest = new RestTemplate();

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

		headers.add("Content-Type", "application/json");

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), Object.class);

		System.out.println(re.toString());
	}
	public void inThree() throws Exception {
		URI uri = new URI("http://127.0.0.1:8080/service/three/threes");

		RestTemplate rest = new RestTemplate();

		Three word = new Three();
		
		word.setContentOne("노");
		word.setContentTwo("트");
		word.setContentThree("북");
		word.setUserName("admin");
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(word);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

		headers.add("Content-Type", "application/json");

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.POST, new HttpEntity<Object>(str, headers), Object.class);

		System.out.println(re.toString());
	}
	public void upThree() throws Exception {
		URI uri = new URI("http://127.0.0.1:8080/service/three/threes/idx");

		RestTemplate rest = new RestTemplate();

		Three word = new Three();
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(word);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

		headers.add("Content-Type", "application/json");

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.PUT, new HttpEntity<Object>(str, headers), Object.class);

		System.out.println(re.toString());

	}
	public void deThree() throws Exception {
		URI uri = new URI("http://127.0.0.1:8080/service/three/threes/idx");

		RestTemplate rest = new RestTemplate();

		Three word = new Three();
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(word);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

		headers.add("Content-Type", "application/json");

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.DELETE, new HttpEntity<Object>(str, headers), Object.class);

		System.out.println(re.toString());
	}
}
