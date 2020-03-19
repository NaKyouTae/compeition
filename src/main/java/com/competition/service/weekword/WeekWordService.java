package com.competition.service.weekword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.process.weekword.WeekWordProcess;

@Service
public class WeekWordService {
	
	@Autowired
	private WeekWordProcess weekWordProcess;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getWeekWords() {
		return (T) weekWordProcess.getWeekWords();
	}
}
