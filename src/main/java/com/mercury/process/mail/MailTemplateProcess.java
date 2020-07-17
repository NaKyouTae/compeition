package com.mercury.process.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.mail.MailTemplate;
import com.mercury.jpa.repository.mail.MailTemplateRepository;

@Component
@SuppressWarnings("unchecked")
public class MailTemplateProcess {
	@Autowired
	private MailTemplateRepository mailTemplateRepository;
	
	public <T extends Object> T seMailTemplateByTempName(String temp) throws Exception {
		try {
			return (T) mailTemplateRepository.findByTempName(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplateByTitle(String title) throws Exception {
		try {
			return (T) mailTemplateRepository.findByTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplateByType(String type) throws Exception {
		try {
			return (T) mailTemplateRepository.findByType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplates() throws Exception {
		try {
			return (T) mailTemplateRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplate(String idx) throws Exception {
		try {
			return (T) mailTemplateRepository.findByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inMailTemplate(MailTemplate temp) throws Exception {
		try {
			return (T) mailTemplateRepository.save(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upMailTemplate(MailTemplate temp) throws Exception {
		try {
			return (T) mailTemplateRepository.save(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deMailTemplate(MailTemplate temp) throws Exception {
		try {
			mailTemplateRepository.delete(temp);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
