package com.competition.service.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.competition.enums.SNSEnum;
import com.competition.jpa.model.user.User;
import com.competition.service.user.UserService;
import com.competition.vo.kakao.KakaoUserVO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nkt
 *
 *	KAKAO API SERVICE
 *
 * Create by User Date : 2020. 6. 23.
 *
 */
@Service
@SuppressWarnings("unchecked")
public class KakaoOAuthService {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * KAKAO USER UPDATE COMPETITION
	 * 
	 * @param <T>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T kakaoUserUp(KakaoUserVO user) throws Exception {
		try {
			User u = new User();
			
			u.setIdx(user.getId());
			u.setUsername(user.getProperties().getNickname());
			u.setPassword(UUID.randomUUID().toString().replace("-", ""));
			if(user.getKakao_account().getEmail_needs_agreement()) {					
				u.setEmail(user.getKakao_account().getEmail());
			}
			u.setSns(SNSEnum.KAKAO);
			
			return (T) userService.upUser(u, null);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	/**
	 * 
	 * KAKAO USER SINGUP COMPETITION
	 * 
	 * @param <T>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T kakaoSignUp(KakaoUserVO user) throws Exception{
		try {
			User SKU = new User();
			
			SKU.setIdx(user.getId());
			SKU.setUsername(user.getProperties().getNickname());
			SKU.setPassword(UUID.randomUUID().toString().replace("-", ""));
			if(user.getKakao_account().getEmail_needs_agreement()) {					
				SKU.setEmail(user.getKakao_account().getEmail());
			}
			SKU.setSns(SNSEnum.KAKAO);
			
			userService.signUp(SKU);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
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
	public <T extends Object> T kakaoLogOut(String access) throws Exception {
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Authorization", "Bearer " + access);
			
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
	public <T extends Object> T getKakaoUserInfo(String access) throws Exception {
		try {
			RestTemplate rest = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			headers.add("Authorization", "Bearer " + access);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);
			
			ResponseEntity<String> rs = rest.postForEntity("https://kapi.kakao.com/v2/user/me", entity, String.class);
			ObjectMapper m = new ObjectMapper();
			KakaoUserVO r = m.readValue(rs.getBody(), KakaoUserVO.class);
			
			return (T) r;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
	public <T extends Object> T getAccessInfo(String access) throws Exception {
		try {
			RestTemplate rest = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			headers.add("Authorization", "Bearer " + access);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);
			
			ResponseEntity<String> rs = rest.exchange("https://kapi.kakao.com/v1/user/access_token_info", HttpMethod.GET, entity, String.class);
			ObjectMapper m = new ObjectMapper();
			Map<String, String> r = m.readValue(rs.getBody(), Map.class);
			
			return (T) r;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T checkAccessExpires(String token) throws Exception {
		try {
			Boolean result = Boolean.FALSE;
			
			Map<String, String> info = getAccessInfo(token);
			
			Integer exp = Integer.parseInt(info.get("expires_in"));
			if(exp > 0) {
				result = Boolean.TRUE;
			}
			
			return (T) result; 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T reIssuanceAccess(String refresh) throws Exception {
		try {
			RestTemplate rest = new RestTemplate();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "refresh_token");
			map.add("client_id", "c4d7328a864db7fd90be93def8e00940");
			map.add("refresh", refresh);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Context-type", "application/json");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			
			ResponseEntity<String> rs = rest.exchange("https://kapi.kakao.com/v1/user/access_token_info", HttpMethod.POST, entity, String.class);
			ObjectMapper m = new ObjectMapper();
			Map<String, String> r = m.readValue(rs.getBody(), Map.class);
			
			return (T) r;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
