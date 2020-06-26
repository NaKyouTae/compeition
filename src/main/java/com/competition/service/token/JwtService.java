package com.competition.service.token;

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

import com.competition.enums.SNSEnum;
import com.competition.jpa.model.user.UserGrade;
import com.competition.jpa.repository.user.UserGradeRepository;
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
	
	@Autowired
	private UserGradeRepository userGradeRepository;
	
	private String accessSecretKey = "CompetitionAccessjwt";
	private byte[] accessSecretBytes = DatatypeConverter.parseBase64Binary(accessSecretKey);
	private SignatureAlgorithm accessSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key accessKey = new SecretKeySpec(accessSecretBytes, accessSignatureAlgorithm.getJcaName());

	private String refreshSecretKey = "CompetitionRefreshjwt";
	private byte[] refreshSecretBytes = DatatypeConverter.parseBase64Binary(refreshSecretKey);
	private SignatureAlgorithm refreshSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key refreshKey = new SecretKeySpec(refreshSecretBytes, refreshSignatureAlgorithm.getJcaName());

	private String userSecretKey = "CompetitionUserjwt";
	private byte[] userSecretBytes = DatatypeConverter.parseBase64Binary(userSecretKey);
	private SignatureAlgorithm userSignatureAlgorithm = SignatureAlgorithm.HS256;
	private final Key userKey = new SecretKeySpec(userSecretBytes, userSignatureAlgorithm.getJcaName());
	
	private String issuer = "Competition";
	
	public <T extends Object> T createUserToken(HttpServletRequest request, HttpServletResponse response, CustomUserDetails user, Date expriation) {
		
		Claims claims = Jwts.claims()
				.setSubject("user")
				.setIssuer(this.issuer)
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
				.setHeaderParam("typ", "USERJWT")
				.setClaims(claims)
				.signWith(userSignatureAlgorithm, userKey)
				.compact();
		
		return (T) jwt;
	}
	
	public <T extends Object> T createAccessToken(HttpServletRequest request, HttpServletResponse response, CustomUserDetails user, Date expriation) {
		
		Claims claims = Jwts.claims()
				.setSubject("access")
				.setIssuer(this.issuer)
				.setAudience(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(expriation);
		
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "ACCESSJWT")
				.setClaims(claims)
				.signWith(accessSignatureAlgorithm, accessKey)
				.compact();
		
		return (T) jwt;
	}
	
	public <T extends Object> T createRefreshToken(HttpServletRequest request, HttpServletResponse response, String user, Date expriation) {
		
		Claims claims = Jwts.claims()
				.setSubject("refresh")
				.setIssuer(this.issuer)
				.setAudience(user)
				.setIssuedAt(new Date())
				.setExpiration(expriation);
		
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "REFRESHJWT")
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
	
	public String getPayload(String token, String type) {
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
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(this.getPayload(token, type));
		return new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
	}
	
	public <T extends Object> T getSns(String token) throws Exception{
		try {
			SNSEnum sns = (SNSEnum) Jwts.parser().setSigningKey(userKey).parseClaimsJws(token).getBody().get("sns");
			return (T) sns;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
