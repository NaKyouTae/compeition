package com.competition.process.three;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.repository.ThreeRepository;
import com.competition.jpa.repository.WeekWordRepository;
import com.competition.jpa.repository.WeekWordRepository.test;
import com.competition.util.DateUtil;

@Component
public class ThreeProcess {
	
	@Autowired
	private ThreeRepository threeRepository;
	
	@Autowired
	private WeekWordRepository weekWordRepository;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getList() {
		test dto = weekWordRepository.findByWord("THREE", DateUtil.now());
		
		return (T) threeRepository.findByWordIdx(dto.getIdx());
	}
}
