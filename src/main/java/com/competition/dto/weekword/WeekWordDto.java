package com.competition.dto.weekword;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WeekWordDto {
	private String idx;
	private String word_group;
	private String word;
	private LocalDateTime insert_date;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
}