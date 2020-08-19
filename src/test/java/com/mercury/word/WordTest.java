package com.mercury.word;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.vo.word.WordVO;

/**
 * 
 * 2020-03-20 CRUD Completed
 * 
 * @author nkt
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordTest {

	@Test
	public void test() throws Exception {
//		seWord();
		inWord();
//		upWord();
//		deWord();
	}

	public void seWord() throws Exception {
		URI uri = new URI("http://localhost:8080/service/weekword/lists");

		RestTemplate rest = new RestTemplate();

		ResponseEntity<Object> re = rest.getForEntity(uri, Object.class);

		System.out.println(re.toString());
	}

	public void inWord() throws Exception {
		URI uri = new URI("http://localhost:8080/service/weekword/words");

		RestTemplate rest = new RestTemplate();

		WordVO vo = new WordVO();

		vo.setWord("노트북");
		vo.setWordGroup("THREE");
		vo.setStartDate("2020-03-23 00:00:00");
		vo.setEndDate("2020-03-29 23:59:59");

		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(vo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(str, headers);

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.POST, entity, Object.class);

		System.out.println(re.toString());

	}

	public void upWord() throws Exception {
		URI uri = new URI("http://localhost:8080/service/weekword/words/4c1513b3-dfb6-4b25-8aee-aec24b323c8f");

		RestTemplate rest = new RestTemplate();

		WordVO vo = new WordVO();

		vo.setIdx("4c1513b3-dfb6-4b25-8aee-aec24b323c8f");
		vo.setWordGroup("THREE");
		vo.setWord("태블릿");
		vo.setStartDate("2020-03-23 00:00:00");
		vo.setEndDate("2020-03-29 23:59:59");

		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(vo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(str, headers);

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.PUT, entity, Object.class);

		System.out.println(re.toString());

	}

	public void deWord() throws Exception {
		URI uri = new URI("http://localhost:8080/service/weekword/words/4c1513b3-dfb6-4b25-8aee-aec24b323c8f");

		RestTemplate rest = new RestTemplate();

		WordVO vo = new WordVO();

		vo.setIdx("4c1513b3-dfb6-4b25-8aee-aec24b323c8f");
		vo.setWordGroup("THREE");
		vo.setWord("태블릿");
		vo.setStartDate("2020-03-23 00:00:00");
		vo.setEndDate("2020-03-29 23:59:59");

		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(vo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(str, headers);

		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.DELETE, entity, Object.class);

		System.out.println(re.toString());

	}

}
