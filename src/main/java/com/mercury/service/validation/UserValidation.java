package com.mercury.service.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercury.jpa.model.user.User;
import com.mercury.process.user.UserProcess;

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
	public static <T extends Object> T checkUserEmailYN(String email)
			throws Exception {
		User user = userProcess.seUserByEmail(email);
		if (user != null)
			return (T) Boolean.TRUE;
		return (T) Boolean.FALSE;
	}
}
