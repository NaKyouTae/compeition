package com.competition.service.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.Love;
import com.competition.process.love.LoveProcess;

@Service
@SuppressWarnings("unchecked")
public class LoveService {
	
	@Autowired
	private LoveProcess loveProcess;
	
	
	public <T extends Object> T seLove(String idx) throws Exception {
		try {
			return (T) loveProcess.seLove(idx);
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T inLove(Love love) throws Exception {
		try {
			return (T) loveProcess.inLove(love);
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T deLove(Love love) throws Exception{
		try {
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
