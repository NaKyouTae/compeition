package com.mercury.service.mail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mail.NewsLetter;
import com.mercury.process.mail.NewsLetterProcess;
import com.mercury.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class NewsLetterService {
	@Autowired
	private NewsLetterProcess newsLetterProcess;

	public <T extends Object> T seNewsLetterByUserName(String userName)
			throws Exception {
		return (T) newsLetterProcess.seNewsLetterByUserName(userName);
	}

	public <T extends Object> T seNewsLetterByUserIdx(String userIdx)
			throws Exception {
		return (T) newsLetterProcess.seNewsLetterByUserIdx(userIdx);
	}

	public <T extends Object> T seNewsLetters() throws Exception {
		return (T) newsLetterProcess.seNewsLetters();
	}

	public <T extends Object> T seNewsLetter(String idx) throws Exception {
		return (T) newsLetterProcess.seNewsLetter(idx);
	}

	public <T extends Object> T inNewsLetter(NewsLetter news) throws Exception {
		news.setSubscribeDate(DateUtil.now());
		news.setIdx(UUID.randomUUID().toString().replace("-", ""));
		return (T) newsLetterProcess.inNewsLetter(news);
	}

	public <T extends Object> T upNewsLetter(NewsLetter news) throws Exception {
		return (T) newsLetterProcess.upNewsLetter(news);
	}

	public <T extends Object> T deNewsLetter(String username) throws Exception {
		NewsLetter news = this.seNewsLetterByUserName(username);
		return (T) newsLetterProcess.deNewsLetter(news);
	}
}
