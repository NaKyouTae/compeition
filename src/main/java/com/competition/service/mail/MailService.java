package com.competition.service.mail;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.UUID;

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
import com.competition.jpa.model.user.User;
import com.competition.service.user.UserService;

@Service
@SuppressWarnings("unchecked")
public class MailService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private JavaMailSender mailSender;
	
//	@Autowired
//	private VelocityEngine velocity;
	
	public MailService() {
		AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext(MailConfig.class);
		this.mailSender = ct.getBean(JavaMailSender.class);
	}
	
	public <T extends Object> T findId(String target) throws Exception {
		try {
			VelocityEngine velocity = new VelocityEngine();
			Integer authNumber = (int)(Math.random() * 100000);
			
			MimeMessage sm = this.mailSender.createMimeMessage();			
			
			MailTemplate temp = mailTemplateService.seMailTemplateByBatchId("FIND_ID");
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			Template velo = velocity.getTemplate(temp.getContent());
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
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
	
	public <T extends Object> T findPW(User user, String target) throws Exception {
		try {
			VelocityEngine velocity = new VelocityEngine();
			
			// 임시 비밀번호 생성 및 적용
			String pw = UUID.randomUUID().toString().subSequence(0, 8).toString();
			user.setPassword(pw);
			
			MimeMessage sm = this.mailSender.createMimeMessage();			
			
			MailTemplate temp = mailTemplateService.seMailTemplateByBatchId("FIND_PW");
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("pw", pw);
			Template velo = velocity.getTemplate(temp.getContent());
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
			StringWriter sw = new StringWriter();
			
			velo.merge(context, sw);
			
			sm.setRecipient(RecipientType.TO, new InternetAddress(target));
			sm.setSubject(temp.getTitle());
			sm.setText(sw.toString(), "UTF-8", "html");
			
			mailSender.send(sm);
			
			userService.upUser(user, null);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T certiNumber(String target) throws Exception {
		try {
			
			// 이메일이 존재 한다면 false;
			Boolean check = userService.checkUserEmail(target);
			if(check) return (T) Boolean.FALSE;
			
			
			VelocityEngine velocity = new VelocityEngine();
			Integer authNumber = (int)(Math.random() * 100000);
			
			MimeMessage sm = this.mailSender.createMimeMessage();			
			
			MailTemplate temp = mailTemplateService.seMailTemplateByBatchId("Certification");
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			Template velo = velocity.getTemplate(temp.getContent());
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
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
	
	public <T extends Object> T drawals(String target) throws Exception {
		try {
			
			VelocityEngine velocity = new VelocityEngine();
			Integer authNumber = (int)(Math.random() * 100000);
			
			MimeMessage sm = this.mailSender.createMimeMessage();			
			
			MailTemplate temp = mailTemplateService.seMailTemplateByBatchId("Drawals");
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			Template velo = velocity.getTemplate(temp.getContent());
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
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
