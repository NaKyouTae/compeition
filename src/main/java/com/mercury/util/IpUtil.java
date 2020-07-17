package com.mercury.util;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
public class IpUtil {

	public static <T extends Object> T getClientIP(HttpServletRequest rq) throws Exception {
		String ip = rq.getHeader("X-Forwarded-For");
		
		try {
			if(ip == null) {
				ip = rq.getHeader("Proxy-Client-IP");
			}
			if(ip == null) {
				ip = rq.getHeader("WL-Proxy-Client-IP");
			}
			if(ip == null) {
				ip = rq.getHeader("HTTP_CLIENT_IP");
			}
			if(ip == null) {
				ip = rq.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if(ip == null) {
				ip = rq.getRemoteAddr();
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (T) ip;
	}
}
