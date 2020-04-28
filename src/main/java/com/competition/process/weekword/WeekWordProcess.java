package com.competition.process.weekword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.weekword.WeekWord;
import com.competition.jpa.repository.weekword.WeekWordRepository;
import com.competition.jpa.repository.weekword.WeekWordRepository.WordInter;

@Component
@SuppressWarnings("unchecked")
public class WeekWordProcess {
	
	@Autowired
	private WeekWordRepository weekWordRepository;
	
	public <T extends Object> T getLists() throws Exception {
		return (T) weekWordRepository.findAll();
	}
	
	public <T extends Object> T getWeekWords(String group) throws Exception {
		WordInter result = weekWordRepository.findByWord(group);
		
//		WeekWordDto word = ObjectUtil.toObject(result, new WeekWordDto());
		WeekWordDto word = new WeekWordDto();
		
		word.setIdx(result.getIdx());
		word.setWordGroup(result.getWordGroup());
		word.setWord(result.getWord());
		word.setInsertDate(result.getInsertDate());
		word.setStartDate(result.getStartDate());
		word.setEndDate(result.getEndDate());
		word.setDescription(result.getDescription());
		
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
