package com.competition.process.three;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.WordThree;
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

	public <T extends Object> T getList() throws Exception {
		WordInter dto = weekWordRepository.findByWord("THREE", DateUtil.now());

		return (T) threeRepository.findByWordIdx(dto.getIdx());
	}

	public <T extends Object> T inThree(WordThree word) throws Exception {
		return (T) threeRepository.save(word);
	}
	public <T extends Object> T upThree(WordThree word) throws Exception {
		return (T) threeRepository.save(word);
	}
	public void deThree(WordThree word) throws Exception {
		threeRepository.delete(word);
	}
}
