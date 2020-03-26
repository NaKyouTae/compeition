package com.competition.service.three;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.WordThree;
import com.competition.process.three.ThreeProcess;
import com.competition.service.weekword.WeekWordService;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class ThreeService {
	
	@Autowired
	private ThreeProcess threeProcess;
	
	@Autowired
	private WeekWordService weekWordService;
	
	public <T extends Object> T getList() throws Exception {
		return (T) threeProcess.getList();
	}
	
	public <T extends Object> T inThree(WordThree three) throws Exception {
		WeekWordDto word = weekWordService.getWeekWords("THREE");
		
		three.setIdx(UUID.randomUUID().toString());
		three.setInsertDate(DateUtil.now());
		three.setWordIdx(word.getIdx());
		// username 어떻게 넣을것인지 
		
		return (T) threeProcess.inThree(three);
	}
	public <T extends Object> T upThree(WordThree word) throws Exception {
		word.setUpdateDate(DateUtil.now());
		return (T) threeProcess.upThree(word);
	}
	public <T extends Object> T deThree(WordThree word) throws Exception {
		try {
			threeProcess.deThree(word);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
	}
}
