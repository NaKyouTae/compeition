package com.competition.service.mail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.competition.handler.MailHandler;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	private static final String FROM_ADD = "qppk123@gmail.com";
	
	public void mailSend() throws MessagingException {
		MailHandler message = new MailHandler(mailSender);
		message.setTo(MailService.FROM_ADD);
		message.setFrom(MailService.FROM_ADD);
		message.setSubject("타이틀 테스트");
		message.setText("TEST", false);
		
		message.send();
	}
}
