package com.competition.process.weekword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.repository.WeekWordRepository;
import com.competition.jpa.repository.WeekWordRepository.test;

@Component
public class WeekWordProcess {
	
	@Autowired
	private WeekWordRepository weekWordRepository;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getWeekWords() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		test result = weekWordRepository.findByWord(now);
		
		WeekWordDto word = new WeekWordDto();
		
		word.setIdx(result.getIdx());
		word.setWord_group(result.getWord_group());
		word.setWord(result.getWord());
		word.setInsert_date(result.getInsert_date());
		word.setStart_date(result.getStart_date());
		word.setEnd_date(result.getEnd_date());
		
		return (T) word;
	}
	
}
