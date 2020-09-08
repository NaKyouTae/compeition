package com.mercury.process.honor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.repository.honor.HonorRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class HonorProcess {
	@Autowired
	private HonorRepository honorRepository;

	public <T extends Object> T seHonors() throws Exception {
		return (T) honorRepository.findAll(
				Sort.by(Sort.Direction.DESC, "startDate", "word", "point"));
	}

	public <T extends Object> T seHonorByIdx(String idx) throws Exception {
		return (T) honorRepository.findByIdx(idx);
	}

	public <T extends Object> T seHonorByUserName(String username)
			throws Exception {
		return (T) honorRepository.findByUserName(username);
	}

	public <T extends Object> T seHonorByContentIdx(String contentIdx)
			throws Exception {
		return (T) honorRepository.findByContentIdx(contentIdx);
	}

	public <T extends Object> T seHonorByWord(String word) throws Exception {
		return (T) honorRepository.findByWord(word);
	}
}
