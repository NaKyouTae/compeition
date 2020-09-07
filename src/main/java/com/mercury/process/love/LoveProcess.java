package com.mercury.process.love;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.love.Love;
import com.mercury.jpa.repository.love.LoveRepository;

@Component
@SuppressWarnings("unchecked")
public class LoveProcess {

	private static final Logger LOGGER = LogManager.getLogger(LoveProcess.class);
	
	@Autowired
	private LoveRepository loveRepository;

	public <T extends Object> T seUserLove(String userIdx, String contentIdx) throws Exception {
		try {
			LOGGER.info("LOVE PROCESS TEST");
			return (T) loveRepository.findByUserIdxAndContentIdx(userIdx, contentIdx);
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
