package com.competition.dto.three;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ThreeDto {
	private String idx;
	private String word_idx;
	private String content_one;
	private String content_two;
	private String content_three;
	private String username;
	private LocalDateTime insert_date;
	private Integer point;
	private boolean love;
}