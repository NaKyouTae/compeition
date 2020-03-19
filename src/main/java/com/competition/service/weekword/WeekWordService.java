package com.competition.service.weekword;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.WeekWord;
import com.competition.process.weekword.WeekWordProcess;
import com.competition.vo.weekword.WeekWordVO;

@Service
@SuppressWarnings("unchecked")
public class WeekWordService {
	
	@Autowired
	private WeekWordProcess weekWordProcess;
	
	public <T extends Object> T getWeekWords() throws Exception {
		return (T) weekWordProcess.getWeekWords();
	}
	
	public <T extends Object> T inWord(WeekWordVO vo) throws Exception {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		WeekWord word = new WeekWord();

		word.setIdx(UUID.randomUUID().toString());
		word.setInsert_date(LocalDateTime.now().format(format));
		word.setWord(vo.getWord());
		word.setWord_group(vo.getWord_group());
		word.setStart_date(LocalDateTime.now().format(format));
		word.setEnd_date(LocalDateTime.now().format(format));
		
		return (T) weekWordProcess.inWord(word);
	}
	public <T extends Object> T upWord(WeekWordVO vo) throws Exception {
		WeekWord word = new WeekWord();

		word.setIdx(vo.getIdx());
		word.setInsert_date(vo.getInsert_date());
		word.setWord(vo.getWord());
		word.setWord_group(vo.getWord_group());
		word.setStart_date(vo.getStart_date());
		word.setEnd_date(vo.getEnd_date());
		
		return (T) weekWordProcess.upWord(word);
	}
	public <T extends Object> T deWord(WeekWordVO vo) throws Exception {
		try {
			WeekWord word = new WeekWord();

			word.setIdx(vo.getIdx());
			word.setInsert_date(vo.getInsert_date());
			word.setWord(vo.getWord());
			word.setWord_group(vo.getWord_group());
			word.setStart_date(vo.getStart_date());
			word.setEnd_date(vo.getEnd_date());
			
			weekWordProcess.deWord(word);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
