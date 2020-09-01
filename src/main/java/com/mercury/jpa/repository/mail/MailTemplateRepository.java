package com.mercury.jpa.repository.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.mail.MailTemplate;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
	MailTemplate findByIdx(String idx);
	MailTemplate findByType(String type);
	MailTemplate findByTempName(String temp);
	MailTemplate findByTitle(String title);
	
}
