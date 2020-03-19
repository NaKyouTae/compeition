package com.competition.process.weekword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.repository.WeekWordRepository;

@Component
public class WeekWordProcess {
	
	@Autowired
	private WeekWordRepository weekWordRepository;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getWeekWords() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		WeekWordDto result = weekWordRepository.findByWord(now);
		
		return (T) result;
	}
	
}
