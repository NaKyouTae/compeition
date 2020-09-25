package com.mercury.jpa.repository.mail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.mail.NewsLetter;

@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetter, Long>{
	NewsLetter findByIdx(String idx);
	List<NewsLetter> findByUserName(String userName);
	List<NewsLetter> findByUserIdx(String userIdx);
}
