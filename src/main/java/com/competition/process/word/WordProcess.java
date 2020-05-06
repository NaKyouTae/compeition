package com.competition.process.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.word.Word;
import com.competition.jpa.repository.word.WordRepository;
import com.competition.jpa.repository.word.WordRepository.WordInter;

@Component
@SuppressWarnings("unchecked")
public class WordProcess {
	
	@Autowired
	private WordRepository weekWordRepository;
	
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
	
	public <T extends Object> T inWord(Word word) throws Exception {
		return (T) weekWordRepository.save(word);
	}
	public <T extends Object> T upWord(Word word) throws Exception {
		return (T) weekWordRepository.save(word);
	}
	public void deWord(Word word) throws Exception {
		weekWordRepository.delete(word);
	}
	
}
