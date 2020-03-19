package com.competition.menu;


import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.competition.vo.menu.MenuVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unused")
public class MenuTest {

	@Test
	public void test() throws Exception {
		seMenu();
//		inMenu();
//		upMenu();
//		deMenu();
		seMenu();
	}
	
	
	public void seMenu() throws Exception {
		
		URI uri = new URI("http://localhost:8080/service/menu/lists");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.GET, entity, Object.class);
		
		System.out.println(re);
	}
	
	public void inMenu() throws Exception {
		
		URI uri = new URI("http://localhost:8080/service/menu/menus");
		
		
		MenuVO vo = new MenuVO();
		
		vo.setP_idx("b2fa9a39-9546-49be-b9f8-af5149a0bbbb");
		vo.setTitle("사용자");
		vo.setUrl("/user");
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(vo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(str, headers);
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.POST, entity, Object.class);
		
		System.out.println(re);
	}
	public void upMenu() throws Exception {
		
		URI uri = new URI("http://localhost:8080/service/menu/menus/dc8f2773-66e9-4482-b4ec-8f3204f7c66b");
		
		
		MenuVO vo = new MenuVO();
		
		vo.setIdx("dc8f2773-66e9-4482-b4ec-8f3204f7c66b");
		vo.setP_idx("b2fa9a39-9546-49be-b9f8-af5149a0bbbb");
		vo.setInsert_date("2020-03-19T16:57:28.349113500");
		vo.setTitle("메뉴");
		vo.setUrl("/menu");
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(vo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(str, headers);
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.PUT, entity, Object.class);
		
		System.out.println(re);
	}
	public void deMenu() throws Exception {
		
		URI uri = new URI("http://localhost:8080/service/menu/menus/367aebc8-9afc-428c-ab72-ba02f3a66cb0");
		
		
		MenuVO vo = new MenuVO();
		
		vo.setIdx("367aebc8-9afc-428c-ab72-ba02f3a66cb0");
		vo.setP_idx("b2fa9a39-9546-49be-b9f8-af5149a0bbbb");
		vo.setInsert_date("2020-03-19T16:59:48.825433700");
		vo.setTitle("사용자");
		vo.setUrl("/user");
		
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(vo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(str, headers);
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Object> re = rest.exchange(uri, HttpMethod.DELETE, entity, Object.class);
		
		System.out.println(re);
	}
}
