package com.competition.mail;


import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.competition.service.mail.MailService;

/**
 * 2020-03-19 CRUD Completed
 * @author nkt
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unused")
public class MenuTest {
	@Autowired
	private MailService mailService;
	
	@Test
	public void test() throws Exception {
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		
		sender.setUsername("qppk123@gmail.com");
		sender.setPassword("skrbxo12");
		
		Properties props = sender.getJavaMailProperties();
		props.put("mail.trasport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		
		mailService.mailSend("qppk123@gmail.com", "kyoutae_93@gmail.com", "test");
	}
	
}
