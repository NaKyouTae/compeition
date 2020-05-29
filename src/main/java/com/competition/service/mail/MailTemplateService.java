package com.competition.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.mail.MailTemplate;
import com.competition.process.mail.MailTemplateProcess;

@Service
@SuppressWarnings("unchecked")
public class MailTemplateService {
	
	@Autowired
	private MailTemplateProcess mailTemplateProcess;
	
	public <T extends Object> T seMailTemplateByBatchId(String batch) throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplateByBatchId(batch);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplateByUsed(String use) throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplateByUsed(use);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplateByType(String type) throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplateByType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplates() throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplates();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplate(String idx) throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplate(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inMailTemplate(MailTemplate temp) throws Exception {
		try {
			return (T) mailTemplateProcess.inMailTemplate(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upMailTemplate(MailTemplate temp) throws Exception {
		try {
			return (T) mailTemplateProcess.upMailTemplate(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deMailTemplate(MailTemplate temp) throws Exception {
		try {
			return (T) mailTemplateProcess.deMailTemplate(temp); 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
