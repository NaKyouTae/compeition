package com.competition.jpa.repository.mail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.mail.MailTemplate;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
	MailTemplate findByIdx(String idx);
	List<MailTemplate> findByType(String type);
	MailTemplate findByTempName(String temp);
	MailTemplate findByTitle(String title);
	
}
