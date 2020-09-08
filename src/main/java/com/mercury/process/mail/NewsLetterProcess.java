package com.mercury.process.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mail.NewsLetter;
import com.mercury.jpa.repository.mail.NewsLetterRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class NewsLetterProcess {
	@Autowired
	private NewsLetterRepository newsLetterRepository;

	public <T extends Object> T seNewsLetterByUserName(String userName)
			throws Exception {
		return (T) newsLetterRepository.findByUserName(userName);
	}

	public <T extends Object> T seNewsLetterByUserIdx(String userIdx)
			throws Exception {
		return (T) newsLetterRepository.findByUserIdx(userIdx);
	}

	public <T extends Object> T seNewsLetters() throws Exception {
		return (T) newsLetterRepository.findAll();
	}

	public <T extends Object> T seNewsLetter(String idx) throws Exception {
		return (T) newsLetterRepository.findByIdx(idx);
	}

	public <T extends Object> T inNewsLetter(NewsLetter news) throws Exception {
		return (T) newsLetterRepository.save(news);
	}

	public <T extends Object> T upNewsLetter(NewsLetter news) throws Exception {
		return (T) newsLetterRepository.save(news);
	}

	public <T extends Object> T deNewsLetter(NewsLetter news) throws Exception {
		newsLetterRepository.delete(news);
		return (T) Boolean.TRUE;
	}
}
