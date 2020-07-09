package com.competition.service.mail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.mail.MailTemplate;
import com.competition.process.mail.MailTemplateProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class MailTemplateService {
	
	@Autowired
	private MailTemplateProcess mailTemplateProcess;
	
	public <T extends Object> T seMailTemplateByTempName(String temp) throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplateByTempName(temp);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seMailTemplateByTitle(String title) throws Exception {
		try {
			return (T) mailTemplateProcess.seMailTemplateByTitle(title);
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
			temp.setIdx(UUID.randomUUID().toString().replace("-", ""));
			temp.setInsertDate(DateUtil.now());
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
