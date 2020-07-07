package com.competition.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("unchecked")
public class EncodingUtil {
	
	@Autowired
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static <T extends Object> T encodingPW(String pw) throws Exception {
		try {
			String p = passwordEncoder.encode(pw);
			return (T) p;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
