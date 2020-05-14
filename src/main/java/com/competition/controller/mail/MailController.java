package com.competition.controller.mail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.service.mail.MailService;

@RestController
@RequestMapping("/service/mails")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@GetMapping("")
	public void send() throws MessagingException {
		mailService.mailSend();
	}
	
}
