package com.competition.enums;

public enum SNSEnum {
	DEFUALT,
	KAKAO,
	NAVER,
	GOOGLE,
	FACEBOOK;
	
	public static SNSEnum getTitle(String title) {
		SNSEnum result = Enum.valueOf(SNSEnum.class, title);
		return result;
	}
}
