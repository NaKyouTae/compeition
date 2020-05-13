package com.competition.service.two;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.two.Two;
import com.competition.process.two.TwoProcess;
import com.competition.service.word.WordService;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class TwoService {
	
	@Autowired
	private TwoProcess twoProcess;
	
	@Autowired
	private WordService weekWordService;
	
	public <T extends Object> T getPopular() throws Exception {
		return (T) twoProcess.getPopular();
	}
	
	public <T extends Object> T getList() throws Exception {
		return (T) twoProcess.getList();
	}
	
	public <T extends Object> T seByUser(String userIdx) throws Exception {
		return (T) twoProcess.seByUser(userIdx);
	}
	
	public <T extends Object> T seByWord() throws Exception {
		return (T) twoProcess.seByWord();
	}
	
	public <T extends Object> T inTwo(Two two) throws Exception {
		WeekWordDto word = weekWordService.getWeekWords("TWO");
		
		two.setIdx(UUID.randomUUID().toString());
		two.setInsertDate(DateUtil.now());
		two.setWordIdx(word.getIdx());
		// username 어떻게 넣을것인지 
		
		return (T) twoProcess.inTwo(two);
	}
	
	public <T extends Object> T upTwo(Two two) throws Exception {
		two.setUpdateDate(DateUtil.now());
		return (T) twoProcess.upTwo(two);
	}
	
	public <T extends Object> T deTwo(Two two) throws Exception {
		try {
			twoProcess.deTwo(two);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
	}
}
