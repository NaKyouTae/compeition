package com.mercury.service.mail;

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
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mercury.common.config.MailConfig;
import com.mercury.jpa.model.mail.MailTemplate;
import com.mercury.jpa.model.user.User;
import com.mercury.service.user.UserService;
import com.mercury.util.EncodingUtil;

/**
 * 
 * Template Location
 * Ex) /bin/main/mailTemplate/username.html 
 * DataBase에 파일명과 확장자만 입력
 * @author nkt
 *	
 */
@Service
@SuppressWarnings("unchecked")
public class MailService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private String tempLocation = "/bin/main/mailTemplate/";
	
	public MailService() {
		AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext(MailConfig.class);
		this.mailSender = ct.getBean(JavaMailSender.class);
	}
	
	public void sendTempMail(String target, String title, String text) throws Exception {
		try {
			MimeMessage sm = this.mailSender.createMimeMessage();
			
			sm.setRecipient(RecipientType.TO, new InternetAddress(target));
			sm.setSubject(title);
			sm.setText(text, "UTF-8", "html");
			
			this.mailSender.send(sm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public <T extends Object> T veloTemp(String temp) throws Exception {
		try {
			VelocityEngine velocity = new VelocityEngine();
			
			velocity.init();
			velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			
			return (T) velocity.getTemplate(tempLocation + temp, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	/**
	 * 아이디 찾기 메일 발송
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T findId(String target) throws Exception {
		try {
			
			// Email이 없다면 False를 Return
			Boolean check = userService.checkUserEmail(target);
			if(!check) return (T) Boolean.FALSE;
			
			User user = userService.seUserByEmail(target);
			// Template에서 사용될 Data
			HashMap<String, Object> model = new HashMap<>();
			model.put("user", user.getUsername());
			
			MailTemplate temp = mailTemplateService.seMailTemplateByType("FindId");
				
			VelocityContext context = new VelocityContext();
			context.put("data", model);
			
			StringWriter sw = new StringWriter();
			Template t = veloTemp(temp.getTempName());
			t.merge(context, sw);
			
			// Send Mail Html Template
			sendTempMail(target, temp.getTitle(), sw.toString());
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	/**
	 * 비밀번호 찾기 메일 발송
	 * 
	 * 
	 * @param <T>
	 * @param user
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T findPW(User user, String target) throws Exception {
		try {
			
			// Email이 없다면 False를 Return
			Boolean check = userService.checkUserEmail(target);
			if(!check) return (T) Boolean.FALSE;
			
			// 임시 비밀번호 생성 및 적용
			String pw = UUID.randomUUID().toString().subSequence(0, 8).toString();
			user.setPassword(EncodingUtil.encodingPW(pw));
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("pw", pw);
			
			MailTemplate temp = mailTemplateService.seMailTemplateByType("FindPw");
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
			
			StringWriter sw = new StringWriter();
			Template t = veloTemp(temp.getTempName());
			t.merge(context, sw);
			
			// Send Mail Html Template
			sendTempMail(target, temp.getTitle(), sw.toString());
			
			userService.upUser(user, null);
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	/**
	 * 
	 * 이메일 인증 메일 발송 
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T certiNumber(String target) throws Exception {
		try {
			
			// Email이 없다면 False를 Return
			Boolean check = userService.checkUserEmail(target);
			if(!check) return (T) Boolean.FALSE;
			
			Integer authNumber = (int)(Math.random() * 100000);

			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			
			MailTemplate temp = mailTemplateService.seMailTemplateByType("Certification");
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
			
			Template t = veloTemp(temp.getTempName());
			StringWriter sw = new StringWriter();
			t.merge(context, sw);
			
			// Send Mail Html Template
			sendTempMail(target, temp.getTitle(), sw.toString());
			
			return (T) authNumber.toString();
		} catch (Exception e) {
			return (T) e;
		}
		
	}
	
	/**
	 * 
	 * 출금 요청 메일 발송 
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T requestWithdraw(String target) throws Exception {
		try {
			
			// Email이 없다면 False를 Return
			Boolean check = userService.checkUserEmail(target);
			if(!check) return (T) Boolean.FALSE;
			
			Integer authNumber = (int)(Math.random() * 100000);
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			
			MailTemplate temp = mailTemplateService.seMailTemplateByType("RequestWithdraw");
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
			
			StringWriter sw = new StringWriter();
			Template t = veloTemp(temp.getTempName());
			t.merge(context, sw);
			
			// Send Mail Html Template
			sendTempMail(target, temp.getTitle(), sw.toString());
			
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	/**
	 * 
	 * 출금 요청 승인 메일 발송
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T approvalWithdraw(String target) throws Exception {
		try {
			
			// Email이 없다면 False를 Return
			Boolean check = userService.checkUserEmail(target);
			if(!check) return (T) Boolean.FALSE;
			
			Integer authNumber = (int)(Math.random() * 100000);
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("auth", authNumber);
			
			MailTemplate temp = mailTemplateService.seMailTemplateByType("ApprovalWithdraw");
			
			VelocityContext context = new VelocityContext();
			context.put("data", model);
			
			StringWriter sw = new StringWriter();
			Template t = veloTemp(temp.getTempName());
			t.merge(context, sw);
			
			// Send Mail Html Template
			sendTempMail(target, temp.getTitle(), sw.toString());
			
			return (T) Boolean.TRUE;
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
