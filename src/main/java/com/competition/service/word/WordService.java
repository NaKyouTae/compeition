package com.competition.service.word;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.word.Word;
import com.competition.process.word.WordProcess;
import com.competition.util.DateUtil;
import com.competition.util.ObjectUtil;
import com.competition.vo.weekword.WeekWordVO;

@Service
@SuppressWarnings("unchecked")
public class WordService {
	
	@Autowired
	private WordProcess weekWordProcess;
	
	public <T extends Object> T getLists() throws Exception {
		return (T) weekWordProcess.getLists();
	}
	
	public <T extends Object> T getWeekWords(String group) throws Exception {
		return (T) weekWordProcess.getWeekWords(group);
	}
	public <T extends Object> T seWord(String wordIdx) throws Exception {
		return (T) weekWordProcess.seWord(wordIdx);
	}
	
	public <T extends Object> T inWord(WeekWordVO vo) throws Exception {
		Word word = ObjectUtil.toObj(vo, new Word());
		
		word.setIdx(UUID.randomUUID().toString().replace("-", ""));
		word.setInsertDate(DateUtil.now());
		
		return (T) weekWordProcess.inWord(word);
	}
	public <T extends Object> T upWord(WeekWordVO vo) throws Exception {
		Word word = ObjectUtil.toObj(vo, new Word());
		
		return (T) weekWordProcess.upWord(word);
	}
	public <T extends Object> T deWord(WeekWordVO vo) throws Exception {
		try {
			Word word = ObjectUtil.toObj(vo, new Word());

			weekWordProcess.deWord(word);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
