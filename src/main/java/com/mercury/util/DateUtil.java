package com.mercury.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author nkt
 * Create Date : 2020-03-20
 */
public interface DateUtil {
	
	
	/**
	 * 현재 날짜 구하기 yyyy-MM-dd HH:mm:ss
	 * @return String
	 * 
	 */
	public static String now() {
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = now.format(formatter);
		
		return date;
	}
}
