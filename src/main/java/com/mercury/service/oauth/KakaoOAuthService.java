package com.mercury.service.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.jpa.model.user.User;
import com.mercury.service.user.UserService;
import com.mercury.vo.kakao.KakaoUserVO;

/**
 * @author nkt
 *
 *         KAKAO API SERVICE
 *
 *         Create by User Date : 2020. 6. 23.
 *
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class KakaoOAuthService {

	@Autowired
	private UserService userService;

	public <T extends Object> T kakaoWithdrawal(User user, String access) throws Exception {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/json");
		headers.add("Authorization", "Bearer " + access);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
		ResponseEntity<String> rs = rest.postForEntity("https://kapi.kakao.com/v1/user/logout", entity, String.class);
		
		ObjectMapper m = new ObjectMapper();
		Map<String, Object> r = m.readValue(rs.getBody(), Map.class);
		
		// 삭제된 사용자와 요청된 사용자의 일렬번호가 다를 경우 False를 Return 한다.
		if(!user.getIdx().equals(r.get("id").toString())) return (T) Boolean.FALSE;
		
		return (T) userService.destoryUser(user);
	}

	/**
	 * 
	 * KAKAO USER UPDATE MERCURY
	 * 
	 * @param <T>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T kakaoUserUp(KakaoUserVO user) throws Exception {
		User u = new User();

		u.setIdx(user.getId());
		u.setUsername(user.getProperties().getNickname());
		u.setPassword(UUID.randomUUID().toString().replace("-", ""));
		if (user.getKakao_account().getEmail_needs_agreement()) {
			u.setEmail(user.getKakao_account().getEmail());
		}
		u.setSns("KAKAO");

		return (T) userService.upUser(u, null);
	}

	/**
	 * 
	 * KAKAO USER SINGUP MERCURY
	 * 
	 * @param <T>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T kakaoSignUp(KakaoUserVO user) throws Exception {
		User SKU = new User();

		SKU.setIdx(user.getId());
		SKU.setUsername(user.getProperties().getNickname());
		SKU.setPassword(UUID.randomUUID().toString().replace("-", ""));
		if (user.getKakao_account().getEmail_needs_agreement()) {
			SKU.setEmail(user.getKakao_account().getEmail());
		}
		SKU.setSns("KAKAO");

		userService.signUp(SKU);

		return (T) Boolean.TRUE;
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
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/json");
		headers.add("Authorization", "Bearer " + access);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

		Object rs = rest.postForEntity("https://kapi.kakao.com/v1/user/logout", entity, Object.class);
		return (T) rs;
	}

	/**
	 * Kakao User Info API Call
	 * 
	 * @param <T>
	 * @param acess
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T getKakaoUserInfo(String access)
			throws Exception {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/json");
		headers.add("Authorization", "Bearer " + access);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(
				null, headers);

		ResponseEntity<String> rs = rest.postForEntity(
				"https://kapi.kakao.com/v2/user/me", entity, String.class);
		ObjectMapper m = new ObjectMapper();
		KakaoUserVO r = m.readValue(rs.getBody(), KakaoUserVO.class);

		return (T) r;
	}

	public <T extends Object> T getAccessInfo(String access) throws Exception {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Authorization", "Bearer " + access);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(
				null, headers);

		ResponseEntity<String> rs = rest.exchange(
				"https://kapi.kakao.com/v1/user/access_token_info",
				HttpMethod.GET, entity, String.class);
		ObjectMapper m = new ObjectMapper();
		Map<String, Object> r = m.readValue(rs.getBody(), Map.class);

		return (T) r;
	}

	public <T extends Object> T checkAccessExpires(String token)
			throws Exception {
		Boolean result = Boolean.FALSE;

		Map<String, Object> info = getAccessInfo(token);
		
		Integer exp = (Integer) info.get("expires_in");
		if (exp > 0) {
			result = Boolean.TRUE;
		}

		return (T) result;
	}

	public <T extends Object> T reIssuanceAccess(String refresh)
			throws Exception {
		RestTemplate rest = new RestTemplate();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "refresh_token");
		map.add("client_id", "c4d7328a864db7fd90be93def8e00940");
		map.add("refresh_token", refresh);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Context-type", "application/x-www-form-urlencoded;charset=utf-8");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map,
				headers);

		ResponseEntity<String> rs = rest.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST, entity, String.class);
		ObjectMapper m = new ObjectMapper();
		Map<String, Object> r = m.readValue(rs.getBody(), Map.class);

		return (T) r;
	}
}
