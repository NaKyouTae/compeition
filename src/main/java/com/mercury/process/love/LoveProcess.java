package com.mercury.process.love;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.love.Love;
import com.mercury.jpa.repository.love.LoveRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class LoveProcess {

	private static final Logger LOGGER = LogManager
			.getLogger(LoveProcess.class);

	@Autowired
	private LoveRepository loveRepository;

	public <T extends Object> T seUserLove(String userIdx, String contentIdx)
			throws Exception {
		return (T) loveRepository.findByUserIdxAndContentIdx(userIdx,
				contentIdx);
	}
	public <T extends Object> T seLove(String contentIdx) throws Exception {
		return (T) loveRepository.findByContentIdx(contentIdx);
	}

	public <T extends Object> T inLove(Love love) throws Exception {
		return (T) loveRepository.save(love);
	}

	public <T extends Object> T deLove(Love love) throws Exception {
		loveRepository.delete(love);
		return (T) Boolean.TRUE;
	}

}
