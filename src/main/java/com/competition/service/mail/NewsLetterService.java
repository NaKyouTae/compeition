package com.competition.service.mail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.mail.NewsLetter;
import com.competition.process.mail.NewsLetterProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class NewsLetterService {
	@Autowired
	private NewsLetterProcess newsLetterProcess;
	
	public <T extends Object> T seNewsLetterByUserName(String userName) throws Exception {
		try {
			return (T) newsLetterProcess.seNewsLetterByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNewsLetterByUserIdx(String userIdx) throws Exception {
		try {
			return (T) newsLetterProcess.seNewsLetterByUserIdx(userIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNewsLetters() throws Exception {
		try {
			return (T) newsLetterProcess.seNewsLetters();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seNewsLetter(String idx) throws Exception {
		try {
			return (T) newsLetterProcess.seNewsLetter(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inNewsLetter(NewsLetter news) throws Exception {
		try {
			news.setSubscribeDate(DateUtil.now());
			news.setIdx(UUID.randomUUID().toString().replace("-", ""));
			return (T) newsLetterProcess.inNewsLetter(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
		
	public <T extends Object> T upNewsLetter(NewsLetter news) throws Exception {
		try {
			return (T) newsLetterProcess.upNewsLetter(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deNewsLetter(String username) throws Exception {
		try {
			NewsLetter news = this.seNewsLetterByUserName(username);
			return (T) newsLetterProcess.deNewsLetter(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
