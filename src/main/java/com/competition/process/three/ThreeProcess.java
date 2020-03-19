package com.competition.process.three;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.repository.ThreeRepository;
import com.competition.jpa.repository.WeekWordRepository;
import com.competition.jpa.repository.WeekWordRepository.test;

@Component
public class ThreeProcess {
	
	@Autowired
	private ThreeRepository threeRepository;
	
	@Autowired
	private WeekWordRepository weekWordRepository;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getList() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		test dto = weekWordRepository.findByWord(now);
		
		return (T) threeRepository.findByWordIdx(dto.getIdx());
	}
}