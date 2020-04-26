package com.competition.process.three;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.Love;
import com.competition.jpa.model.WordThree;
import com.competition.jpa.repository.LoveRepository;
import com.competition.jpa.repository.ThreeRepository;
import com.competition.jpa.repository.WeekWordRepository;
import com.competition.jpa.repository.WeekWordRepository.WordInter;
import com.competition.util.DateUtil;

@Component
@SuppressWarnings("unchecked")
public class ThreeProcess {

	@Autowired
	private ThreeRepository threeRepository;

	@Autowired
	private WeekWordRepository weekWordRepository;
	
	@Autowired
	private LoveRepository loveRepository;
	
	public <T extends Object> T getList() throws Exception {
		WordInter dto = weekWordRepository.findByWord("THREE");
		
		List<WordThree> three = threeRepository.findByWordIdx(dto.getIdx());
		
		return (T) three;
	}

	public <T extends Object> T inThree(WordThree three) throws Exception {
		return (T) threeRepository.save(three);
	}
	public <T extends Object> T upThree(WordThree three) throws Exception {
		
		WordThree dbThree = threeRepository.findByIdx(three.getIdx());
		
		if(!dbThree.getPoint().equals(three.getPoint())) {
			
			Love love = new Love();
			love.setIdx(UUID.randomUUID().toString().replace("-", ""));
			love.setInsertDate(DateUtil.now());
			love.setContentIdx(three.getIdx());
			love.setUserIdx(three.getUserIdx());
			
			loveRepository.save(love);
		}
		
		return (T) threeRepository.save(three);
	}
	public void deThree(WordThree three) throws Exception {
		threeRepository.delete(three);
	}
}
