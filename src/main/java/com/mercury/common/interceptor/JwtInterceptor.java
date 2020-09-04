package com.mercury.common.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;
import com.mercury.service.oauth.KakaoOAuthService;
import com.mercury.service.token.JwtService;
import com.mercury.service.token.TokenBlockService;
import com.mercury.service.token.TokenRefreshService;
import com.mercury.service.user.UserService;
import com.mercury.user.CustomUserDetails;
import com.mercury.vo.kakao.KakaoUserVO;

import io.jsonwebtoken.Claims;

public class JwtInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private JwtService jwtService;
	@Autowired
	private JwtService jwtUtill;
	@Autowired
	private TokenBlockService blockTokenService;
	@Autowired
	private TokenRefreshService refreshTokenService;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private KakaoOAuthService kakaoOAuthService;
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		
//		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4300");
//		response.addHeader("Access-Control-Allow-Credentials", "true");
		
		String Access = request.getHeader("AWT");
		String Refresh = request.getHeader("RWT");
		String User = request.getHeader("UWT");
		
		
		String accessToken = "";
		String refreshToken = "";
		String userToken = "";
		
		
		// login이 되었을 경우
		if(Access != null && Refresh != null && User != null) {
			
			Long a_exp = Long.valueOf(systemConfigRepository.findByConfigName("AWT_EXPRIATION").getConfigValue());
			
			// ValidateToken을 이용해서  유효성 확인 필요
			Claims u_body = jwtUtill.getUserPayLoad(User);
			
			String loginType = request.getHeader("loginType");
			
			// 로그인 형태가 자체 로그인 일 경우
			if(loginType.equals("default")) {
				// Refresh Token DB에 존재 하는지 체크
				// Refresh Token이 Block List에 등록 되어있는지 체크
				Boolean refreshCheck = jwtService.validateToken(Refresh, "Refresh");
				if(refreshCheck) {
					Boolean isRefresh = refreshTokenService.isRefreshToken(Refresh);
					Boolean isBlock = blockTokenService.isBlockToken(Refresh);
					
					if(!isRefresh && isBlock) throw new Exception("로그인 유효기간이 완료되었습니다.");
				}else {
					throw new Exception("로그인 유효기간이 완료되었습니다.");
				}
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				// 매번 유저 정보를 확인하여 기존 USER TOKEN과 새로운 USER TOKEN을 비교하여 다를 경우 새로운 USER TOKEN을 RETURN 한다.
				
				// REFRESH TOKEN이 DATA BASE에 있는 지 확인
				TokenRefresh reTokenInfo = refreshTokenService.seRefreshToken(Refresh);
				
				// REFRESH TOKEN의 USERNAME을 가지고 USER 정보 조회
				CustomUserDetails user = (CustomUserDetails) userSerivce.loadUserByUsername(reTokenInfo.getUserName());
				
				// USER 정보를 이용하여 USER TOKEN을 생성
				userToken = jwtUtill.createUserToken(user, new Date(u_body.getExpiration().getTime()));
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// Access Token 유효 시간 체크
				// 이전 USER TOKEN과 새로운 USER TOKEN을 비교
				// 사용자 정보 재 조회 후 Access Token, User Token 재 발급
				Boolean accessCheck = jwtService.validateToken(Access, "Access");
				if(!accessCheck ) {
					accessToken = jwtUtill.createAccessToken(user.getUsername(), new Date(System.currentTimeMillis() + a_exp));
//					response.setHeader("AWT", accessToken);
					response.addCookie(new Cookie("AWT", accessToken));
					
				}
				
				Claims nu_body = jwtUtill.getUserPayLoad(userToken);
				Boolean userCheck = jwtService.validateToken(Access, "Access");
				if(!userCheck || !u_body.equals(nu_body)) {
					userToken = jwtUtill.createUserToken(user, new Date(System.currentTimeMillis() + a_exp));
//					response.setHeader("UWT", userToken);
					response.addCookie(new Cookie("UWT", userToken));
				}
				
				
			// 로그인 형태가 KAKAO 로그인 일 경우
			}else if(loginType.equals("kakao")) {
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				// 매번 유저 정보를 확인하여 기존 USER TOKEN과 새로운 USER TOKEN을 비교하여 다를 경우 새로운 USER TOKEN을 RETURN 한다. 
				
				// 카카오 사용자 정보 조회 API 호출
				KakaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(accessToken);
				
				// 카카오 사용자 정보를 DATA BASE에 저장
				User user = kakaoOAuthService.kakaoUserUp(kUser);
				
				// 카카오 사용자 아이디를 이용하여 DATA BASE에 사용자 정보 조회
				CustomUserDetails cUser = (CustomUserDetails) userSerivce.loadUserByUsername(user.getUsername());
				
				// 사용자 정보를 이용하여 새로운 USER TOKEN 발급
				userToken = jwtUtill.createUserToken(cUser, u_body.getExpiration());
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// Access Token 유효 기간 체크
				// 이전 USER TOKEN과 새로운 USER TOKEN을 비교
				// KAKAO API CALL
				if(kakaoOAuthService.checkAccessExpires(Access) == Boolean.FALSE || !User.equals(userToken)) {
					
					// Access Token ReIssuance
					Map<String, String> reissu = kakaoOAuthService.reIssuanceAccess(refreshToken);
					
					accessToken = reissu.get("access_token");
					userToken = jwtUtill.createUserToken(cUser, u_body.getExpiration());
					
					if(!Refresh.equals(reissu.get("refresh_token"))) {
						refreshToken = reissu.get("refresh_token");
//						response.setHeader("RWT", refreshToken);
						response.addCookie(new Cookie("RWT", refreshToken));
					}
					
//					response.setHeader("AWT", accessToken);
//					response.setHeader("UWT", userToken);
					response.addCookie(new Cookie("AWT", accessToken));
					response.addCookie(new Cookie("UWT", userToken));
				}
			}
		}
		
		return true;
	}
}
