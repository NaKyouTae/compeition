package com.mercury.service.token;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.user.UserGrade;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;
import com.mercury.jpa.repository.user.UserGradeRepository;
import com.mercury.service.user.UserService;
import com.mercury.user.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@SuppressWarnings("unchecked")
public class JwtService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@Autowired
	private UserGradeRepository userGradeRepository;
	
	private String refreshSecretKey = "MercuryRefreshjwt"; 
//			systemConfigRepository.findByConfigName("RWT_SECRET").getConfigValue();
	private byte[] refreshSecretBytes = DatatypeConverter.parseBase64Binary(refreshSecretKey);
	private SignatureAlgorithm refreshSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key refreshKey = new SecretKeySpec(refreshSecretBytes, refreshSignatureAlgorithm.getJcaName());

	private String accessSecretKey = "MercuryAccessjwt";
//	systemConfigRepository.findByConfigName("AWT_SECRET").getConfigValue();
	private byte[] accessSecretBytes = DatatypeConverter.parseBase64Binary(accessSecretKey);
	private SignatureAlgorithm accessSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key accessKey = new SecretKeySpec(accessSecretBytes, accessSignatureAlgorithm.getJcaName());

	private String userSecretKey = "MercuryUserjwt"; 
//			systemConfigRepository.findByConfigName("UWT_SECRET").getConfigValue();
	private byte[] userSecretBytes = DatatypeConverter.parseBase64Binary(userSecretKey);
	private SignatureAlgorithm userSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key userKey = new SecretKeySpec(userSecretBytes, userSignatureAlgorithm.getJcaName());
	
	public <T extends Object> T createUserToken(HttpServletRequest request, HttpServletResponse response, CustomUserDetails user, Date expriation) {
		
		Claims claims = Jwts.claims()
				.setSubject(systemConfigRepository.findByConfigName("UWT_SUBJECT").getConfigValue())
				.setIssuer(systemConfigRepository.findByConfigName("ISSUER").getConfigValue())
				.setAudience(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(expriation);

		List<String> authList = new ArrayList<>();
		UserGrade grade = userGradeRepository.findByUserName(user.getUsername());
		
		user.getAuthorities().stream().forEach(item -> {
			authList.add(item.getAuthority());
		});
		
		claims.put("roles", authList);
		claims.put("grade", grade);
		claims.put("user", user.getUser());
		
		String jwt = Jwts.builder()
				.setHeaderParam("typ", systemConfigRepository.findByConfigName("UWT_TYPE").getConfigValue())
				.setClaims(claims)
				.signWith(userSignatureAlgorithm, userKey)
				.compact();
		
		return (T) jwt;
	}
	
	public <T extends Object> T createAccessToken(HttpServletRequest request, HttpServletResponse response, String username, Date expriation) {
		
		Claims claims = Jwts.claims()
				.setSubject(systemConfigRepository.findByConfigName("AWT_SUBJECT").getConfigValue())
				.setIssuer(systemConfigRepository.findByConfigName("ISSUER").getConfigValue())
				.setAudience(username)
				.setIssuedAt(new Date())
				.setExpiration(expriation);
		
		String jwt = Jwts.builder()
				.setHeaderParam("typ", systemConfigRepository.findByConfigName("AWT_TYPE").getConfigValue())
				.setClaims(claims)
				.signWith(accessSignatureAlgorithm, accessKey)
				.compact();
		
		return (T) jwt;
	}
	
	public <T extends Object> T createRefreshToken(HttpServletRequest request, HttpServletResponse response, String username, Date expriation) {
		
		Claims claims = Jwts.claims()
				.setSubject(systemConfigRepository.findByConfigName("RWT_SUBJECT").getConfigValue())
				.setIssuer(systemConfigRepository.findByConfigName("ISSUER").getConfigValue())
				.setAudience(username)
				.setIssuedAt(new Date())
				.setExpiration(expriation);
		
		String jwt = Jwts.builder()
				.setHeaderParam("typ", systemConfigRepository.findByConfigName("RWT_TYPE").getConfigValue())
				.setClaims(claims)
				.signWith(refreshSignatureAlgorithm, refreshKey)
				.compact();
		
		return (T) jwt;
	}
	
	public boolean validateToken(String token, String type){
		try {
			Jws<Claims> claims = null;
			
			if(type.equals("Access")) {
				claims = Jwts.parser().setSigningKey(accessKey).parseClaimsJws(token);
			} else if(type.equals("Refresh")) {
				claims = Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(token);
			}else if(type.equals("User")) {
				claims = Jwts.parser().setSigningKey(userKey).parseClaimsJws(token);
			}
				
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUserName(String token, String type) {
		String result = "";
		
		if(type.equals("Access")) {
			result = Jwts.parser().setSigningKey(accessKey).parseClaimsJws(token).getBody().getAudience();
		}else if(type.equals("Refresh")) {
			result = Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(token).getBody().getAudience();
		}else if(type.equals("User")) {
			result = Jwts.parser().setSigningKey(userKey).parseClaimsJws(token).getBody().getAudience();
		}
		return result;
	}
	
	public Authentication getAuthentication(String token, String type) {
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(this.getUserName(token, type));
		return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
	}
	
	public <T extends Object> T getUserPayLoad(String token) throws Exception{
		try {
			return (T) Jwts.parser().setSigningKey(userKey).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
