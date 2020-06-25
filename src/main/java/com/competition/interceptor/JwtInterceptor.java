package com.competition.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.competition.enums.SNSEnum;
import com.competition.jpa.model.token.RefreshToken;
import com.competition.jpa.model.user.User;
import com.competition.service.oauth.KakaoOAuthService;
import com.competition.service.token.JwtService;
import com.competition.service.token.black.BlackTokenService;
import com.competition.service.token.refresh.RefreshTokenService;
import com.competition.service.user.UserService;
import com.competition.user.CustomUserDetails;
import com.competition.vo.kakao.KakaoUserVO;

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
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

		String Access = request.getHeader("AWT");
		String Refresh = request.getHeader("RWT");
		String User = request.getHeader("UWT");
		
		
		String accessToken = "";
		String refreshToken = "";
		String userToken = "";
		
		SNSEnum sns = jwtUtill.getSns(User);
		
		// login이 되었을 경우
		if(Access != null && Refresh != null) {
			// 로그인 형태가 자체 로그인 일 경우
			if(sns.equals(SNSEnum.DEFUALT)) {
				// Refresh Token DB에 존재 하는지 체크
				// Refresh Token이 Black List에 등록 되어있는지 체크
				if(jwtService.validateToken(Refresh, "Refresh")) {
					Boolean isRefresh = refreshTokenService.isRefreshToken(Refresh);
					Boolean isBlack = blackTokenService.isBlackToken(Refresh);
					
					if(!isRefresh && isBlack) throw new Exception("로그인 유효기간이 완료되었습니다.");
				}
				
				// Access Token 유효성 체크
				// 사용자 정보 재 조회 후 Access Token, User Token 재 발급
				if(!jwtService.validateToken(Access, "Access")) {
					RefreshToken reTokenInfo = refreshTokenService.seRefreshToken(Refresh);
					CustomUserDetails user = (CustomUserDetails) userSerivce.loadUserByUsername(reTokenInfo.getUserName());
					
					accessToken = jwtUtill.createAccessToken(request, response, user, new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30)));
					userToken = jwtUtill.createUserToken(request, response, user, jwtUtill.getSns(User), new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30)));
				}
				
				return true;
			// 로그인 형태가 KAKAO 로그인 일 경우
			}else if(sns.equals(SNSEnum.KAKAO)) {
				// Access Token 유효 기간 체크 
				// KAKAO API CALL
				if(kakaoOAuthService.checkAccessExpires(Access) == Boolean.FALSE) {
					
					// Access Token ReIssuance
					Map<String, String> reissu = kakaoOAuthService.reIssuanceAccess(refreshToken);
					
					KakaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(accessToken);
					
					User user = kakaoOAuthService.kakaoUserUp(kUser);
					CustomUserDetails cUser = (CustomUserDetails) userSerivce.loadUserByUsername(user.getUsername());

					accessToken = reissu.get("access_token");
					userToken = jwtUtill.createUserToken(request, response, cUser, jwtUtill.getSns(User), new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 30)));
					
					if(!Refresh.equals(reissu.get("refresh_token"))) {
						refreshToken = reissu.get("refresh_token");
						response.setHeader("RWT", refreshToken);
					}
				}
			}
			response.setHeader("AWT", accessToken);
			response.setHeader("UWT", userToken);
		}
		return true;
	}
}
