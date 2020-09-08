package com.mercury.service.love;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.love.Love;
import com.mercury.jpa.model.three.Three;
import com.mercury.process.love.LoveProcess;
import com.mercury.service.three.ThreeService;
import com.mercury.service.two.TwoService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class LoveService {

	@Autowired
	private LoveProcess loveProcess;

	@Autowired
	private ThreeService threeService;

	@Autowired
	private TwoService twoService;

	public <T extends Object> T seTotalLove(String userIdx) throws Exception {
		Integer three = threeService.getTotalPoint(userIdx) == null
				? 0
				: threeService.getTotalPoint(userIdx);
		Integer two = twoService.getTotalPoint(userIdx) == null
				? 0
				: twoService.getTotalPoint(userIdx);
		Integer result = three + two;
		return (T) result;
	}

	public <T extends Object> T seLove(String idx) throws Exception {
		return (T) loveProcess.seLove(idx);
	}

	public <T extends Object> T checkLove(String contentIdx, String userIdx)
			throws Exception {
		Boolean result = Boolean.FALSE;

		Love love = loveProcess.seUserLove(userIdx, contentIdx);
		if (love != null) {
			result = Boolean.TRUE;
		}

		return (T) result;
	}

	public <T extends Object> T inLove(Love love) throws Exception {
		return (T) loveProcess.inLove(love);
	}

	public <T extends Object> T deLove(String userIdx, String contentIdx)
			throws Exception {
		Love love = loveProcess.seUserLove(userIdx, contentIdx);

		Three three = threeService.seThreeByIdx(contentIdx);
		three.setPoint(three.getPoint() - 1);

		if (three.getPoint() == 0) {
			three.setLove(Boolean.FALSE);
		}

		threeService.upThree(three);

		return (T) loveProcess.deLove(love);
	}
}
