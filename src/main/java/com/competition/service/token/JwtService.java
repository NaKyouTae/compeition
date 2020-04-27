package com.competition.service.token;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	
	@Autowired
	private UserService userService;
	
	private String secretKey = "competitionjwt";
	
	private byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	
	private final Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	
	public String createToken(HttpServletRequest request, HttpServletResponse response, CustomUserDetails user, Date expriation) {
		
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("roles", user.getAuthorities());
		
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setSubject(user.getUsername()).setClaims(claims)
				.setExpiration(expriation).signWith(signatureAlgorithm, key).compact();
		
		return jwt;		
	}
	
	public boolean validateToken(String token){
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUserInfo(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}
	
	public Authentication getAuthentication(String token) {
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(this.getUserInfo(token));
		return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
	}
}
