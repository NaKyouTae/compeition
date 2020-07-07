package com.competition.service.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.competition.jpa.model.user.User;
import com.competition.process.user.UserProcess;

@SuppressWarnings("unchecked")
public class UserValidation {
	
	@Autowired
	private static UserProcess userProcess;
	
	/**
	 * 
	 * DataBase에 이메일이 존재 하는 지 여부를 판단하여 true, false로 return
	 * 
	 * @param <T>
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static <T extends Object> T checkUserEmailYN(String email) throws Exception{
		try {
			User user = userProcess.seUserByEmail(email);
			if(user != null) return (T) Boolean.TRUE;
			return (T) Boolean.FALSE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
