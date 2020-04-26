package com.competition.dto.weekword;

import lombok.Data;

@Data
public class WeekWordDto {
	private String idx;
	private String wordGroup;
	private String word;
	private String insertDate;
	private String startDate;
	private String endDate;
	private String description;
}