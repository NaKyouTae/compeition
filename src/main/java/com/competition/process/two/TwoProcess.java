package com.competition.process.two;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.love.Love;
import com.competition.jpa.model.two.Two;
import com.competition.jpa.model.user.User;
import com.competition.jpa.repository.love.LoveRepository;
import com.competition.jpa.repository.two.TwoRepository;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.word.WordRepository;
import com.competition.jpa.repository.word.WordRepository.WordInter;
import com.competition.util.DateUtil;

@Component
@SuppressWarnings("unchecked")
public class TwoProcess {

	@Autowired
	private TwoRepository twoRepository;

	@Autowired
	private WordRepository weekWordRepository;
	
	@Autowired
	private LoveRepository loveRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public <T extends Object> T getPopular() throws Exception {
		WordInter dto = weekWordRepository.findByWord("TWO");
		
		List<Two> two = twoRepository.findByWordIdx(dto.getIdx(), Sort.by(Sort.Direction.DESC, "point"));
		
		return (T) two;
	}
	
	public <T extends Object> T getList() throws Exception {
		return (T) twoRepository.findAll();
	}
	
	public <T extends Object> T seByUser(String userIdx) throws Exception {
		List<Two> two = twoRepository.findByUserIdx(userIdx, Sort.by(Sort.Direction.DESC, "insertDate"));
		
		return (T) two;
	}
	
	public <T extends Object> T seByWord() throws Exception {
		WordInter dto = weekWordRepository.findByWord("TWO");
		
		List<Two> two = twoRepository.findByWordIdx(dto.getIdx(), Sort.by(Sort.Direction.DESC, "insertDate"));
		
		return (T) two;
	}

	public <T extends Object> T inTwo(Two two) throws Exception {
		return (T) twoRepository.save(two);
	}
	
	public <T extends Object> T upTwo(Two two) throws Exception {
		
		Two dbTwo = twoRepository.findByIdx(two.getIdx());
		
		if(!dbTwo.getPoint().equals(two.getPoint())) {
			
			Love love = new Love();
			love.setIdx(UUID.randomUUID().toString().replace("-", ""));
			love.setInsertDate(DateUtil.now());
			love.setContentIdx(two.getIdx());
			
			User user = userRepository.findByUsername(two.getLoveName());
			
			love.setUserIdx(user.getIdx());
			
			loveRepository.save(love);
			two.setLoveName("");
		}
		
		return (T) twoRepository.save(two);
	}
	
	public void deTwo(Two two) throws Exception {
		twoRepository.delete(two);
	}
}
