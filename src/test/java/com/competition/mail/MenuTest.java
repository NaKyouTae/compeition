package com.competition.mail;


import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		
		Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", 587); 
    	props.put("mail.smtp.starttls.enable", "false");
    	props.put("mail.smtp.auth", "false");

		
//		mailService.mailSend();
	}
	
}
