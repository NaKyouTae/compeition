package com.competition.service.three;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.three.Three;
import com.competition.process.three.ThreeProcess;
import com.competition.service.word.WordService;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class ThreeService {
	
	@Autowired
	private ThreeProcess threeProcess;
	
	@Autowired
	private WordService weekWordService;
	
	public <T extends Object> T getTotalPoint(String userIdx) throws Exception {
		try {
			return (T) threeProcess.getTotalPoint(userIdx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T getPopular() throws Exception {
		return (T) threeProcess.getPopular();
	}
	
	public <T extends Object> T getList() throws Exception {
		return (T) threeProcess.getList();
	}

	public <T extends Object> T seByUser(String userIdx) throws Exception {
		return (T) threeProcess.seByUser(userIdx);
	}
	
	public <T extends Object> T seByWord() throws Exception {
		return (T) threeProcess.seByWord();
	}
	
	public <T extends Object> T inThree(Three three) throws Exception {
		WeekWordDto word = weekWordService.getWeekWords("THREE");
		
		three.setIdx(UUID.randomUUID().toString().replace("-", ""));
		three.setInsertDate(DateUtil.now());
		three.setWordIdx(word.getIdx());
		three.setWord(word.getWord());
		
		return (T) threeProcess.inThree(three);
	}
	public <T extends Object> T upThree(Three three) throws Exception {
		three.setUpdateDate(DateUtil.now());
		return (T) threeProcess.upThree(three);
	}
	public <T extends Object> T deThree(Three three) throws Exception {
		try {
			threeProcess.deThree(three);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
	}
}
