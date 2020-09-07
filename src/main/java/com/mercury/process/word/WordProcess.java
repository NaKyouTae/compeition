package com.mercury.process.word;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.word.Word;
import com.mercury.jpa.repository.word.WordRepository;

@Component
@SuppressWarnings("unchecked")
public class WordProcess {
	
	@Autowired
	private WordRepository weekWordRepository;
	
	public <T extends Object> T getLists() throws Exception {
		return (T) weekWordRepository.findAll();
	}
	
	public <T extends Object> T getWeekWords(String group) throws Exception {
		
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		Word word = weekWordRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndWordGroup(now, now, group);
		
		return (T) word;
	}
	
	public <T extends Object> T seWord(String wordIdx) throws Exception {
		try {
			return (T) weekWordRepository.findByIdx(wordIdx);
		} catch (Exception e) {
			return (T) e;
		}
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
