package com.mercury.service.mail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mail.MailTemplate;
import com.mercury.process.mail.MailTemplateProcess;
import com.mercury.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class MailTemplateService {

	@Autowired
	private MailTemplateProcess mailTemplateProcess;

	public <T extends Object> T seMailTemplateByTempName(String temp)
			throws Exception {
		return (T) mailTemplateProcess.seMailTemplateByTempName(temp);
	}

	public <T extends Object> T seMailTemplateByTitle(String title)
			throws Exception {
		return (T) mailTemplateProcess.seMailTemplateByTitle(title);
	}

	public <T extends Object> T seMailTemplateByType(String type)
			throws Exception {
		return (T) mailTemplateProcess.seMailTemplateByType(type);
	}

	public <T extends Object> T seMailTemplates() throws Exception {
		return (T) mailTemplateProcess.seMailTemplates();
	}

	public <T extends Object> T seMailTemplate(String idx) throws Exception {
		return (T) mailTemplateProcess.seMailTemplate(idx);
	}

	public <T extends Object> T inMailTemplate(MailTemplate temp)
			throws Exception {
		temp.setIdx(UUID.randomUUID().toString().replace("-", ""));
		temp.setInsertDate(DateUtil.now());
		return (T) mailTemplateProcess.inMailTemplate(temp);
	}

	public <T extends Object> T upMailTemplate(MailTemplate temp)
			throws Exception {
		return (T) mailTemplateProcess.upMailTemplate(temp);
	}

	public <T extends Object> T deMailTemplate(MailTemplate temp)
			throws Exception {
		return (T) mailTemplateProcess.deMailTemplate(temp);
	}
}
