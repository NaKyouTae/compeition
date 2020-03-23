package com.competition.service.three;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.WordThree;
import com.competition.process.three.ThreeProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class ThreeService {
	
	@Autowired
	private ThreeProcess threeProcess;
	
	public <T extends Object> T getList() throws Exception {
		return (T) threeProcess.getList();
	}
	
	public <T extends Object> T inThree(WordThree word) throws Exception {
		
		word.setIdx(UUID.randomUUID().toString());
		word.setInsertDate(DateUtil.now());
		
		return (T) threeProcess.inThree(word);
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
