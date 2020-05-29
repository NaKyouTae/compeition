package com.competition.config.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.competition.jpa.model.system.config.SystemConfig;
import com.competition.jpa.repository.system.config.SystemConfigRepository;

public class MailConfig {
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		SystemConfig hostConfig = systemConfigRepository.findByConfigName("MailHost");
		SystemConfig portConfig = systemConfigRepository.findByConfigName("MailPort");
		
		// smtp.gmail.com
		sender.setHost(hostConfig.getConfigValue());
		// 587
		sender.setPort(Integer.parseInt(portConfig.getConfigValue()));
		
		
		SystemConfig userConfig = systemConfigRepository.findByConfigName("MailUser");
		SystemConfig pwConfig = systemConfigRepository.findByConfigName("MailPw");
		
		sender.setUsername(userConfig.getConfigValue());
		sender.setPassword(pwConfig.getConfigValue());
		
		Properties props = sender.getJavaMailProperties();
		props.put("mail.trasport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return sender;
	}
	
}
