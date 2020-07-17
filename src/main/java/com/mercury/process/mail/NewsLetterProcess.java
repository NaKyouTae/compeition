package com.mercury.process.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.mail.NewsLetter;
import com.mercury.jpa.repository.mail.NewsLetterRepository;

@Component
@SuppressWarnings("unchecked")
public class NewsLetterProcess {
	@Autowired
	private NewsLetterRepository newsLetterRepository;
	
	public <T extends Object> T seNewsLetterByUserName(String userName) throws Exception {
		try {
			return (T) newsLetterRepository.findByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNewsLetterByUserIdx(String userIdx) throws Exception {
		try {
			return (T) newsLetterRepository.findByUserIdx(userIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNewsLetters() throws Exception {
		try {
			return (T) newsLetterRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNewsLetter(String idx) throws Exception {
		try {
			return (T) newsLetterRepository.findByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inNewsLetter(NewsLetter news) throws Exception {
		try {
			return (T) newsLetterRepository.save(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upNewsLetter(NewsLetter news) throws Exception {
		try {
			return (T) newsLetterRepository.save(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deNewsLetter(NewsLetter news) throws Exception {
		try {
			newsLetterRepository.delete(news);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	
}
