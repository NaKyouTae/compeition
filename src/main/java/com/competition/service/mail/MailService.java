package com.competition.service.mail;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.competition.config.MailConfig;
import com.competition.jpa.model.mail.MailTemplate;

@Service
@SuppressWarnings("unchecked")
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
//	@Autowired
//	private VelocityEngine velocity;
	
	public MailService() {
		AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext(MailConfig.class);
		this.mailSender = ct.getBean(JavaMailSender.class);
	}
	
	
	public <T extends Object> T mailSend(String target) throws Exception {
		try {
			
			VelocityEngine velocity = new VelocityEngine();
			Integer authNumber = (int)(Math.random() * 100000);
			
			MimeMessage sm = this.mailSender.createMimeMessage();			
			
			MailTemplate temp = mailTemplateService.seMailTemplateByBatchId("Authenticated");
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			Template velo = velocity.getTemplate(temp.getContent());
			
			VelocityContext context = new VelocityContext();
			context.put("test", model);
			StringWriter sw = new StringWriter();
			
			velo.merge(context, sw);
			
			sm.setRecipient(RecipientType.TO, new InternetAddress(target));
			sm.setSubject(temp.getTitle());
			sm.setText(sw.toString(), "UTF-8", "html");
			
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
