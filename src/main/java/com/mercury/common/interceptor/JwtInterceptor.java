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
import com.mercury.util.CookieUtil;
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
		String accHeaderToken = request.getHeader("AWT");
		String refHeaderToken = request.getHeader("RWT");
		String useHeaderToken = request.getHeader("UWT");
		
		String nAccessToken = "";
		String nRefreshToken = "";
		String nUserToken = "";
		
		Cookie accessCookie;
		Cookie refreshCookie;
		Cookie userCookie;
		
		// login이 되었을 경우
		if(accHeaderToken != null && refHeaderToken != null && useHeaderToken != null) {
			
			Long a_exp = Long.valueOf(systemConfigRepository.findByConfigName("AWT_EXPRIATION").getConfigValue());
			
			// ValidateToken을 이용해서  유효성 확인 필요
			Claims u_body = jwtUtill.getUserPayLoad(useHeaderToken);
			
			String loginType = request.getHeader("loginType");
			
			// 로그인 형태가 자체 로그인 일 경우
			if(loginType.equals("default")) {
				// Refresh Token DB에 존재 하는지 체크
				// Refresh Token이 Block List에 등록 되어있는지 체크
				Boolean refreshCheck = jwtService.validateToken(refHeaderToken, "Refresh");
				if(refreshCheck) {
					Boolean isRefresh = refreshTokenService.isRefreshToken(refHeaderToken);
					Boolean isBlock = blockTokenService.isBlockToken(refHeaderToken);
					
					if(!isRefresh && isBlock) throw new Exception("로그인 유효기간이 완료되었습니다.");
				}else {
					throw new Exception("로그인 유효기간이 완료되었습니다.");
				}
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				// 매번 유저 정보를 확인하여 기존 USER TOKEN과 새로운 USER TOKEN을 비교하여 다를 경우 새로운 USER TOKEN을 RETURN 한다.
				
				// REFRESH TOKEN이 DATA BASE에 있는 지 확인
				TokenRefresh reTokenInfo = refreshTokenService.seRefreshToken(refHeaderToken);
				
				// REFRESH TOKEN의 USERNAME을 가지고 USER 정보 조회
				CustomUserDetails user = (CustomUserDetails) userSerivce.loadUserByUsername(reTokenInfo.getUserName());
				
				// USER 정보를 이용하여 USER TOKEN을 생성
				nUserToken = jwtUtill.createUserToken(user, new Date(u_body.getExpiration().getTime()));
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// Access Token 유효 시간 체크
				// 이전 USER TOKEN과 새로운 USER TOKEN을 비교
				// 사용자 정보 재 조회 후 Access Token, User Token 재 발급
				Boolean accessCheck = jwtService.validateToken(accHeaderToken, "Access");
				if(!accessCheck ) {
					nAccessToken = jwtUtill.createAccessToken(user.getUsername(), new Date(System.currentTimeMillis() + a_exp));
					accessCookie = new CookieUtil.Builder().domain(request.getRemoteHost()).path("/").name("AWT").value(nAccessToken).maxAge((int) new Date(System.currentTimeMillis() + a_exp).getTime()).build().getCookie();
					response.addCookie(accessCookie);
					
				}
				
				Claims nu_body = jwtUtill.getUserPayLoad(nUserToken);
				Boolean userCheck = jwtService.validateToken(accHeaderToken, "Access");
				if(!userCheck || !u_body.equals(nu_body)) {
					nUserToken = jwtUtill.createUserToken(user, new Date(System.currentTimeMillis() + a_exp));
//					response.setHeader("UWT", userToken);
					
					userCookie = new CookieUtil.Builder().domain(request.getRemoteHost()).path("/").name("UWT").value(nUserToken).maxAge((int) u_body.getExpiration().getTime()).build().getCookie();
					response.addCookie(userCookie);
				}
				
				
			// 로그인 형태가 KAKAO 로그인 일 경우
			}else if(loginType.equals("kakao")) {
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				// 매번 유저 정보를 확인하여 기존 USER TOKEN과 새로운 USER TOKEN을 비교하여 다를 경우 새로운 USER TOKEN을 RETURN 한다. 
				///////////////////////////////////////////////////////////////////////////////////////////////////////
				
				if(kakaoOAuthService.checkAccessExpires(accHeaderToken) == Boolean.FALSE) {
					Map<String, Object> reissu = kakaoOAuthService.reIssuanceAccess(refHeaderToken);
					
					// 기존 Refresh Token과 새로들어온 Refresh Token이 다르면 Refresh Token을 갱신					
					if(!refHeaderToken.equals(reissu.get("refresh_token"))) {
						// Refresh Token Create
						nRefreshToken = reissu.get("refresh_token").toString();
						System.out.println("[kakao] refresh token reIssu : " + nRefreshToken);
						refreshCookie = new CookieUtil.Builder().domain(request.getRemoteHost()).path("/").name("RWT").value(nRefreshToken).maxAge(((Integer) reissu.get("expires_in") * 7)).build().getCookie();
						response.addCookie(refreshCookie);
					}
					
					// Access Token Create
					nAccessToken = reissu.get("access_token").toString();
					System.out.println("[kakao] access token reissu : " + nAccessToken);
					accessCookie = new CookieUtil.Builder().domain(request.getRemoteHost()).path("/").name("AWT").value(nAccessToken).maxAge((Integer) reissu.get("expires_in")).build().getCookie();
					response.addCookie(accessCookie);

					
					KakaoUserVO kUser = kakaoOAuthService.getKakaoUserInfo(nAccessToken);
					// 카카오 사용자 정보를 DATA BASE에 저장
					User user = kakaoOAuthService.kakaoUserUp(kUser);
					
					// 카카오 사용자 아이디를 이용하여 DATA BASE에 사용자 정보 조회
					CustomUserDetails cUser = (CustomUserDetails) userSerivce.loadUserByUsername(user.getUsername());
					
					// User Token Create					
					nUserToken = jwtUtill.createUserToken(cUser, u_body.getExpiration());
					System.out.println("[kakao] user token reissu : " + nUserToken);
					userCookie = new CookieUtil.Builder().domain(request.getRemoteHost()).path("/").name("UWT").value(nUserToken).maxAge((int) u_body.getExpiration().getTime()).build().getCookie();
					response.addCookie(userCookie);
				}
			}
		}
		
		return true;
	}
}
