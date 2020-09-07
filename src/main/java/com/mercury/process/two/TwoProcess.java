package com.mercury.process.two;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.love.Love;
import com.mercury.jpa.model.two.Two;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.word.Word;
import com.mercury.jpa.repository.love.LoveRepository;
import com.mercury.jpa.repository.two.TwoRepository;
import com.mercury.jpa.repository.user.UserRepository;
import com.mercury.jpa.repository.word.WordRepository;
import com.mercury.util.DateUtil;

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
	
	public <T extends Object> T getTotalPoint(String userIdx) throws Exception {
		try {
			return (T) twoRepository.getTotalPoint(userIdx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T getPopular() throws Exception {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Word dto = weekWordRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndWordGroup(now, now, "TWO");
		
		List<Two> two = twoRepository.findByWordIdx(dto.getIdx(), Sort.by(Sort.Direction.DESC, "point", "insertDate"));
		
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
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Word dto = weekWordRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndWordGroup(now, now, "TWO");
		
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
	
	public <T extends Object> T deTwo(Two two) throws Exception {
		try {
			twoRepository.delete(two);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			 e.printStackTrace();
			 return (T) e;
		}
	}
	
	public <T extends Object> T deTwoAllEntities(List<Two> twos) throws Exception {
		try {
			twoRepository.deleteAll(twos);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
