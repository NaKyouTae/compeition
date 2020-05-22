package com.competition.service.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.competition.config.mail.MailConfig;
import com.competition.jpa.model.system.config.SystemConfig;
import com.competition.jpa.repository.system.config.SystemConfigRepository;

@Service
@SuppressWarnings("unchecked")
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	public MailService() {
		AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext(MailConfig.class);
		this.mailSender = ct.getBean(JavaMailSender.class);
	}
	
	
	public <T extends Object> T mailSend(String target) throws Exception {
		try {
			Integer authNumber = (int)(Math.random() * 100000);
			
			SystemConfig mailTitle = systemConfigRepository.findByConfigName("emailCheckTitle");
			SystemConfig mailContent = systemConfigRepository.findByConfigName("emailCheckContent");
			
			SimpleMailMessage sm = new SimpleMailMessage();
			
			sm.setTo(target);
			sm.setSubject("tset");
			sm.setText(authNumber.toString());
			
			mailSender.send(sm);
			
			return (T) authNumber.toString();
		} catch (Exception e) {
			return (T) e;
		}
		
	}
	
	public void sendWithAttch(String to, String subject, String text, String attch) throws Exception {
		MimeMessage m = mailSender.createMimeMessage();
		
		MimeMessageHelper h = new MimeMessageHelper(m, true);
		
		h.setTo(to);
		h.setSubject(subject);
		h.setText(text);
		
		FileSystemResource file = new FileSystemResource(new File(attch));
		
		h.addAttachment("Invoice", file);
		
		mailSender.send(m);
	}
}
