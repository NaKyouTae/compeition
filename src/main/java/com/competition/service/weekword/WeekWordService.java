package com.competition.service.weekword;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.WeekWord;
import com.competition.process.weekword.WeekWordProcess;
import com.competition.util.DateUtil;
import com.competition.vo.weekword.WeekWordVO;

@Service
@SuppressWarnings("unchecked")
public class WeekWordService {
	
	@Autowired
	private WeekWordProcess weekWordProcess;
	
	public <T extends Object> T getLists() throws Exception {
		return (T) weekWordProcess.getLists();
	}
	
	public <T extends Object> T getWeekWords(String group) throws Exception {
		return (T) weekWordProcess.getWeekWords(group);
	}
	
	public <T extends Object> T inWord(WeekWordVO vo) throws Exception {
		WeekWord word = new WeekWord();

		word.setIdx(UUID.randomUUID().toString());
		word.setInsert_date(DateUtil.now());
		word.setWord(vo.getWord());
		word.setWord_group(vo.getWord_group());
		word.setStart_date(vo.getStart_date());
		word.setEnd_date(vo.getEnd_date());		
		word.setDescription(vo.getDescription());
		
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
		word.setDescription(vo.getDescription());
		
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
			word.setDescription(vo.getDescription());
			
			weekWordProcess.deWord(word);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
