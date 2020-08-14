package com.mercury.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("unchecked")
public class EncodingUtil {
	
	@Autowired
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static <T extends Object> T encodingPW(String pw) throws Exception {
		try {
			return (T) passwordEncoder.encode(pw);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public static <T extends Object> T matches(String prevPw, String pw) throws Exception {
		try {
			Boolean yn = passwordEncoder.matches(prevPw, pw);
			return (T) yn;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
