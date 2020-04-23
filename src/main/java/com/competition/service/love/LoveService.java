package com.competition.service.love;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.Love;
import com.competition.jpa.model.User;
import com.competition.jpa.repository.UserRepository;
import com.competition.process.love.LoveProcess;

@Service
@SuppressWarnings("unchecked")
public class LoveService {
	
	@Autowired
	private LoveProcess loveProcess;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public <T extends Object> T seLove(String idx, String username) throws Exception {
		try {
			Boolean result = Boolean.FALSE;
			
			User user = userRepository.findByUsername(username);
			
			List<Love> loves = loveProcess.seLove(idx);
			
			if(user != null && loves.size() > 0) {
				result = loves.stream().anyMatch(love -> love.getUserIdx().equals(user.getIdx()));
			}
			
			return (T) result;
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