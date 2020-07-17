package com.mercury.common.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mercury.jpa.model.token.TokenRefresh;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;
import com.mercury.service.oauth.KakaoOAuthService;
import com.mercury.service.token.JwtService;
import com.mercury.service.token.black.BlackTokenService;
import com.mercury.service.token.refresh.RefreshTokenService;
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
	private BlackTokenService blackTokenService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private UserService userSerivce;
	@Autowired
	private KakaoOAuthService kakaoOAuthService;
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

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
			Map u = (Map) u_body.get("user");
			
			// 로그인 형태가 자체 로그인 일 경우
			if(u.get("sns").equals("DEFAULT")) {
				// Refresh Token DB에 존재 하는지 체크
				// Refresh Token이 Black List에 등록 되어있는지 체크
				if(jwtService.validateToken(Refresh, "Refresh")) {
					Boolean isRefresh = refreshTokenService.isRefreshToken(Refresh);
					Boolean isBlack = blackTokenService.isBlackToken(Refresh);
					
					if(!isRefresh && isBlack) throw new Exception("로그인 유효기간이 완료되었습니다.");
				}
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				// 매번 유저 정보를 확인하여 기존 USER TOKEN과 새로운 USER TOKEN을 비교하여 다를 경우 새로운 USER TOKEN을 RETURN 한다.
				
				// REFRESH TOKEN이 DATA BASE에 있는 지 확인
				TokenRefresh reTokenInfo = refreshTokenService.seRefreshToken(Refresh);
				
				// REFRESH TOKEN의 USERNAME을 가지고 USER 정보 조회
				CustomUserDetails user = (CustomUserDetails) userSerivce.loadUserByUsername(reTokenInfo.getUserName());
				
				// USER 정보를 이용하여 USER TOKEN을 생성
				userToken = jwtUtill.createUserToken(request, response, user, u_body.getExpiration());
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// Access Token 유효 시간 체크
				// 이전 USER TOKEN과 새로운 USER TOKEN을 비교
				// 사용자 정보 재 조회 후 Access Token, User Token 재 발급
				if(!jwtService.validateToken(Access, "Access") || !User.equals(userToken)) {
					
					accessToken = jwtUtill.createAccessToken(request, response, user.getUsername(), new Date(System.currentTimeMillis() + a_exp));
					
					response.setHeader("AWT", accessToken);
					response.setHeader("UWT", userToken);
				}
				
			// 로그인 형태가 KAKAO 로그인 일 경우
			}else if(u.get("sns").equals("KAKAO")) {
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				// 매번 유저 정보를 확인하여 기존 USER TOKEN과 새로운 USER TOKEN을 비교하여 다를 경우 새로운 USER TOKEN을 RETURN 한다. 
				
				// 카카오 사용자 정보 조회 API 호출
				KakaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(accessToken);
				
				// 카카오 사용자 정보를 DATA BASE에 저장
				User user = kakaoOAuthService.kakaoUserUp(kUser);
				
				// 카카오 사용자 아이디를 이용하여 DATA BASE에 사용자 정보 조회
				CustomUserDetails cUser = (CustomUserDetails) userSerivce.loadUserByUsername(user.getUsername());
				
				// 사용자 정보를 이용하여 새로운 USER TOKEN 발급
				userToken = jwtUtill.createUserToken(request, response, cUser, u_body.getExpiration());
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// Access Token 유효 기간 체크
				// 이전 USER TOKEN과 새로운 USER TOKEN을 비교
				// KAKAO API CALL
				if(kakaoOAuthService.checkAccessExpires(Access) == Boolean.FALSE || !User.equals(userToken)) {
					
					// Access Token ReIssuance
					Map<String, String> reissu = kakaoOAuthService.reIssuanceAccess(refreshToken);
					
					accessToken = reissu.get("access_token");
					userToken = jwtUtill.createUserToken(request, response, cUser, u_body.getExpiration());
					
					if(!Refresh.equals(reissu.get("refresh_token"))) {
						refreshToken = reissu.get("refresh_token");
						response.setHeader("RWT", refreshToken);
					}
					
					response.setHeader("AWT", accessToken);
					response.setHeader("UWT", userToken);
				}
			}
		}
		return true;
	}
}
