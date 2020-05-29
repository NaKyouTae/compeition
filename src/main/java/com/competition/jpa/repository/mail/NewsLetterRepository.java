package com.competition.jpa.repository.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.newsletter.NewsLetter;

@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetter, Long>{
	NewsLetter findByIdx(String idx);
	NewsLetter findByUserName(String userName);
	NewsLetter findByUserIdx(String userIdx);
}
