package com.competition.process.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.Love;
import com.competition.jpa.repository.LoveRepository;

@Component
@SuppressWarnings("unchecked")
public class LoveProcess {

	@Autowired
	private LoveRepository loveRepository;

	public <T extends Object> T seLove(String idx) throws Exception {
		return (T) loveRepository.findByContentIdx(idx);
	}
	public <T extends Object> T inLove(Love love) throws Exception {
		return (T) loveRepository.save(love);
	}

	public void deLove(Love love) throws Exception {
		loveRepository.delete(love);
	}
	
}
