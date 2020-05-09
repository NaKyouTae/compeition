package com.competition.service.token;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@SuppressWarnings("unchecked")
public class JwtService {
	
	@Autowired
	private UserService userService;
	
	private String accessSecretKey = "CompetitionAccessjwt";
	private byte[] accessSecretBytes = DatatypeConverter.parseBase64Binary(accessSecretKey);
	private SignatureAlgorithm accessSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key accessKey = new SecretKeySpec(accessSecretBytes, accessSignatureAlgorithm.getJcaName());

	private String refreshSecretKey = "CompetitionRefreshjwt";
	private byte[] refreshSecretBytes = DatatypeConverter.parseBase64Binary(refreshSecretKey);
	private SignatureAlgorithm refreshSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key refreshKey = new SecretKeySpec(refreshSecretBytes, refreshSignatureAlgorithm.getJcaName());
	
	public <T extends Object> T createAccessToken(HttpServletRequest request, HttpServletResponse response, CustomUserDetails user, Date expriation) {
		
		List<String> authList = new ArrayList<>();

		user.getAuthorities().stream().forEach(item -> {
			authList.add(item.getAuthority());
		});
		
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("roles", authList);
		
		String jwt = Jwts.builder().setHeaderParam("typ", "ACCESSJWT" + UUID.randomUUID().toString().replace("-", "")).setSubject(user.getUsername()).setClaims(claims)
				.setExpiration(expriation).signWith(accessSignatureAlgorithm, accessKey).compact();
		
		return (T) jwt;
	}
	public <T extends Object> T createRefreshToken(HttpServletRequest request, HttpServletResponse response, String user, Date expriation) {
		
		Claims claims = Jwts.claims().setSubject(user);
		claims.put("roles", user);
		
		String jwt = Jwts.builder().setHeaderParam("typ", "REFRESHJWT" + UUID.randomUUID().toString().replace("-", "")).setSubject(user).setClaims(claims)
				.setExpiration(expriation).signWith(refreshSignatureAlgorithm, refreshKey).compact();
		
		return (T) jwt;
	}
	
	public boolean validateToken(String token, String type){
		try {
			Jws<Claims> claims = null;
			
			if(type.equals("Access")) {
				claims = Jwts.parser().setSigningKey(accessKey).parseClaimsJws(token);
			}else if(type.equals("Refresh")) {
				claims = Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(token);
			}
				
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUserInfo(String token, String type) {
		String result = "";
		
		if(type.equals("Access")) {
			result = Jwts.parser().setSigningKey(accessKey).parseClaimsJws(token).getBody().getSubject();
		}else if(type.equals("Refresh")) {
			result = Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(token).getBody().getSubject();
		}
		return result;
	}
	
	public Authentication getAuthentication(String token, String type) {
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(this.getUserInfo(token, type));
		return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
	}
}
