package com.mercury.process.three;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.love.Love;
import com.mercury.jpa.model.three.Three;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.word.Word;
import com.mercury.jpa.repository.love.LoveRepository;
import com.mercury.jpa.repository.three.ThreeRepository;
import com.mercury.jpa.repository.user.UserRepository;
import com.mercury.jpa.repository.word.WordRepository;
import com.mercury.util.DateUtil;

@Component
@SuppressWarnings("unchecked")
public class ThreeProcess {
	private static final Logger LOGGER = LogManager.getLogger(ThreeProcess.class);
	
	@Autowired
	private ThreeRepository threeRepository;

	@Autowired
	private WordRepository weekWordRepository;
	
	@Autowired
	private LoveRepository loveRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public <T extends Object> T getTotalPoint(String userIdx) throws Exception {
		try {
			return (T) threeRepository.getTotalPoint(userIdx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T getPopular() throws Exception {
		try {
			Word dto = weekWordRepository.findByWord("THREE");
			
			List<Three> three = new ArrayList<>(); 
			if(dto == null) {
				three = null; 
			}else {			
				three =	threeRepository.findByWordIdx(dto.getIdx(), Sort.by(Sort.Direction.DESC, "point", "insertDate"));
			}
			
			return (T) three;
		} catch (Exception e) {
			return(T) e;
		}
	}
	
	public <T extends Object> T getList() throws Exception {
		return (T) threeRepository.findAll();
	}
	
	public <T extends Object> T seByUser(String userIdx) throws Exception {
		List<Three> three = threeRepository.findByUserIdx(userIdx, Sort.by(Sort.Direction.DESC, "insertDate"));
		
		return (T) three;
	}
	
	public <T extends Object> T seThreeByIdx(String idx) throws Exception {
		try {
			return (T) threeRepository.findByIdx(idx); 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seByWord() throws Exception {
		Word dto = weekWordRepository.findByWord("THREE");
		
		List<Three> three = new ArrayList<>(); 
		if(dto == null) {
			three = null; 
		}else {			
			three =	threeRepository.findByWordIdx(dto.getIdx(), Sort.by(Sort.Direction.DESC, "insertDate"));
		}
		
		return (T) three;
	}

	public <T extends Object> T inThree(Three three) throws Exception {
		return (T) threeRepository.save(three);
	}
	public <T extends Object> T upThree(Three three) throws Exception {
		
		Three dbThree = threeRepository.findByIdx(three.getIdx());
		
		if(!dbThree.getPoint().equals(three.getPoint())) {
			
			Love love = new Love();
			love.setIdx(UUID.randomUUID().toString().replace("-", ""));
			love.setInsertDate(DateUtil.now());
			love.setContentIdx(three.getIdx());
			
			User user = userRepository.findByUsername(three.getLoveName());
			
			love.setUserIdx(user.getIdx());
			
			loveRepository.save(love);
			three.setLoveName("");
		}
		
		return (T) threeRepository.save(three);
	}
	
	public <T extends Object> T deThree(Three three) throws Exception {
		try {
			threeRepository.delete(three);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
	public <T extends Object> T deThreeAllEntities(List<Three> threes) throws Exception {
		try {
			threeRepository.deleteAll(threes);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
}
