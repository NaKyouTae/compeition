package com.competition.service.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.love.Love;
import com.competition.jpa.model.user.User;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.process.love.LoveProcess;
import com.competition.service.three.ThreeService;
import com.competition.service.two.TwoService;

@Service
@SuppressWarnings("unchecked")
public class LoveService {
	
	@Autowired
	private LoveProcess loveProcess;
	
	@Autowired
	private ThreeService threeService;
	
	@Autowired
	private TwoService twoService;
	
	@Autowired
	private UserRepository userRepository;
	
	public <T extends Object> T seTotalLove(String userIdx) throws Exception{
		try {
			Integer three = threeService.getTotalPoint(userIdx);
			Integer two = twoService.getTotalPoint(userIdx);
			Integer result = three + two;
			return (T) result;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seLove(String contentIdx, String username) throws Exception {
		try {
			Boolean result = Boolean.FALSE;
			
			User user = userRepository.findByUsername(username);
			
			Love love = loveProcess.seLove(contentIdx);
			
			if(user != null && love != null) {
				result = love.getUserIdx().equals(user.getIdx());
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
