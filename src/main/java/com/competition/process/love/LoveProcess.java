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

	public <T extends Object> T seLove(String contentIdx) throws Exception {
		return (T) loveRepository.findByContentIdx(contentIdx);
	}
	public <T extends Object> T inLove(Love love) throws Exception {
		return (T) loveRepository.save(love);
	}

	public void deLove(Love love) throws Exception {
		loveRepository.delete(love);
	}
	
}
