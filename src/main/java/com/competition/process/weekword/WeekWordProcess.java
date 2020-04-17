package com.competition.process.weekword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.WeekWord;
import com.competition.jpa.repository.WeekWordRepository;
import com.competition.jpa.repository.WeekWordRepository.WordInter;

@Component
@SuppressWarnings("unchecked")
public class WeekWordProcess {
	
	@Autowired
	private WeekWordRepository weekWordRepository;
	
	public <T extends Object> T getLists() throws Exception {
		return (T) weekWordRepository.findAll();
	}
	
	public <T extends Object> T getWeekWords(String group) throws Exception {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = LocalDateTime.now().format(format);
		WordInter result = weekWordRepository.findByWord("THREE", now.toString());
		
		WeekWordDto word = new WeekWordDto();
		
		word.setIdx(result.getIdx());
		word.setWord_group(result.getWord_group());
		word.setWord(result.getWord());
		word.setInsert_date(result.getInsert_date());
		word.setStart_date(result.getStart_date());
		word.setEnd_date(result.getEnd_date());
		
		return (T) word;
	}
	
	public <T extends Object> T inWord(WeekWord word) throws Exception {
		return (T) weekWordRepository.save(word);
	}
	public <T extends Object> T upWord(WeekWord word) throws Exception {
		return (T) weekWordRepository.save(word);
	}
	public void deWord(WeekWord word) throws Exception {
		weekWordRepository.delete(word);
	}
	
}
