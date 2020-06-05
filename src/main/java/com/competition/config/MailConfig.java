package com.competition.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
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
		
		return sender;
	}
//	@Autowired
//	SystemConfigRepository systemConfigRepository;a
//	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl sender = new JavaMailSenderImpl();
//		
//		SystemConfig hostConfig = systemConfigRepository.findByConfigName("MailHost");
//		SystemConfig portConfig = systemConfigRepository.findByConfigName("MailPort");
//		
//		// smtp.gmail.com
//		sender.setHost(hostConfig.getConfigValue());
//		// 587
//		sender.setPort(Integer.parseInt(portConfig.getConfigValue()));
//		
//		
//		SystemConfig userConfig = systemConfigRepository.findByConfigName("MailUser");
//		SystemConfig pwConfig = systemConfigRepository.findByConfigName("MailPw");
//		
//		sender.setUsername(userConfig.getConfigValue());
//		sender.setPassword(pwConfig.getConfigValue());
//		
//		Properties props = sender.getJavaMailProperties();
//		props.put("mail.trasport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
//		
//		return sender;
//	}
	
}
