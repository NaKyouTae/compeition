package com.competition.service.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.love.Love;
import com.competition.jpa.model.three.Three;
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
			Integer three = threeService.getTotalPoint(userIdx) == null ?  0 : threeService.getTotalPoint(userIdx);
			Integer two = twoService.getTotalPoint(userIdx) == null ?  0 : twoService.getTotalPoint(userIdx);
			Integer result = three + two;
			return (T) result;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seLove(String idx) throws Exception {
		try {
			return (T) loveProcess.seLove(idx);
		}catch(Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T checkLove(String contentIdx, String userIdx) throws Exception {
		try {
			Boolean result = Boolean.FALSE;
			
			Love love = loveProcess.seUserLove(userIdx, contentIdx);
			if(love != null) {
				result = Boolean.TRUE;
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
	
	public <T extends Object> T deLove(String userIdx, String contentIdx) throws Exception{
		try {
			Love love = loveProcess.seUserLove(userIdx, contentIdx);
			
			Three three = threeService.seThreeByIdx(contentIdx);
			three.setPoint(three.getPoint() - 1);
			
			if(three.getPoint() == 0) {
				three.setLove(Boolean.FALSE);
			}
			
			threeService.upThree(three);
			
			return (T) loveProcess.deLove(love);
		}catch(Exception e) {
			return (T) e;
		}
		
	}
}
