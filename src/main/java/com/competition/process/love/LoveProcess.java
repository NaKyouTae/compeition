package com.competition.process.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.love.Love;
import com.competition.jpa.repository.love.LoveRepository;

@Component
@SuppressWarnings("unchecked")
public class LoveProcess {

	@Autowired
	private LoveRepository loveRepository;

	public <T extends Object> T seUserLove(String userIdx, String contentIdx) throws Exception {
		try {
			return (T) loveRepository.findByUserLove(userIdx, contentIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seLove(String contentIdx) throws Exception {
		try {
			return (T) loveRepository.findByContentIdx(contentIdx);
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
	public <T extends Object> T inLove(Love love) throws Exception {
		try {
			return (T) loveRepository.save(love);
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}

	public <T extends Object> T deLove(Love love) throws Exception {
		try {
			loveRepository.delete(love);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
		
	}
	
}
