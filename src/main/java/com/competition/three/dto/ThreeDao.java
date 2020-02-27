package com.competition.three.dto;

import lombok.Data;

@Data
public class ThreeDao {
	// 내용
	private String content;
	
	// 사용자 명
	private String username;
	
	// 작성 일자 yyyy-mm-dd
	private String insertdate;
	
	// 점수
	private String point;
}