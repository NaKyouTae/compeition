package com.mercury.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;

@Component
public class MailConfig {
	
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//		JavaMailSenderImpl sender = new JavaMailSenderImpl();
//		
//		sender.setHost("smtp.gmail.com");
//		sender.setPort(587);
//		
//		sender.setUsername("qppk123@gmail.com");
//		sender.setPassword("skrbxo12!@");
//		
//		Properties props = sender.getJavaMailProperties();
//		props.put("mail.trasport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
//		
//		return sender;
//	}
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		SystemConfig hostConfig = systemConfigRepository.findByConfigName("MAIL_HOST");
		SystemConfig portConfig = systemConfigRepository.findByConfigName("MAIL_PORT");
		
		// smtp.gmail.com
		sender.setHost(hostConfig.getConfigValue());
		// 587
		sender.setPort(Integer.parseInt(portConfig.getConfigValue()));
		
		
		SystemConfig userConfig = systemConfigRepository.findByConfigName("MAIL_ADDRESS");
		SystemConfig pwConfig = systemConfigRepository.findByConfigName("MAIL_PASSWORD");
		
		sender.setUsername(userConfig.getConfigValue());
		sender.setPassword(pwConfig.getConfigValue());
		
		Properties props = sender.getJavaMailProperties();
		props.put("mail.trasport.protocol", systemConfigRepository.findByConfigName("MAIL_SMTP_PROTOCOL").getConfigValue());
		props.put("mail.smtp.auth", systemConfigRepository.findByConfigName("MAIL_SMTP_AUTH").getConfigValue());
		props.put("mail.smtp.starttls.enable", systemConfigRepository.findByConfigName("MAIL_SMTP_STARTTLS_ENABLE").getConfigValue());
		props.put("mail.debug", systemConfigRepository.findByConfigName("MAIL_DEBUG").getConfigValue());
		
		return sender;
	}
	
}
