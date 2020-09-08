package com.mercury.process.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mail.MailTemplate;
import com.mercury.jpa.repository.mail.MailTemplateRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class MailTemplateProcess {
	@Autowired
	private MailTemplateRepository mailTemplateRepository;

	public <T extends Object> T seMailTemplateByTempName(String temp)
			throws Exception {
		return (T) mailTemplateRepository.findByTempName(temp);
	}

	public <T extends Object> T seMailTemplateByTitle(String title)
			throws Exception {
		return (T) mailTemplateRepository.findByTitle(title);
	}

	public <T extends Object> T seMailTemplateByType(String type)
			throws Exception {
		return (T) mailTemplateRepository.findByType(type);
	}

	public <T extends Object> T seMailTemplates() throws Exception {
		return (T) mailTemplateRepository.findAll();
	}

	public <T extends Object> T seMailTemplate(String idx) throws Exception {
		return (T) mailTemplateRepository.findByIdx(idx);
	}

	public <T extends Object> T inMailTemplate(MailTemplate temp)
			throws Exception {
		return (T) mailTemplateRepository.save(temp);
	}

	public <T extends Object> T upMailTemplate(MailTemplate temp)
			throws Exception {
		return (T) mailTemplateRepository.save(temp);
	}

	public <T extends Object> T deMailTemplate(MailTemplate temp)
			throws Exception {
		mailTemplateRepository.delete(temp);
		return (T) Boolean.TRUE;
	}
}
