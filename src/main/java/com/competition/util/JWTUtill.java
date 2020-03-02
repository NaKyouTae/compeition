package com.competition.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import com.competition.jpa.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtill {
	
	private String secretKey = "competitionjwt";
	
	private byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	
	private final Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	
	public String getUserToken(HttpServletRequest request, HttpServletResponse response, User user) {
		
		String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setSubject(user.getUsername()).claim("uid", user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24))).signWith(signatureAlgorithm, key).compact();
		
		return jwt;		
	}
}
