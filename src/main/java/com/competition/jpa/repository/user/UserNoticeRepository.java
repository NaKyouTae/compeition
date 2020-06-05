package com.competition.jpa.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.user.UserNotice;

@Repository
public interface UserNoticeRepository extends JpaRepository<UserNotice, Long> {
	UserNotice findByIdx(String idx);
	List<UserNotice> findByUserIdx(String idx);
	List<UserNotice> findByUserName(String name);
	UserNotice findByNoticeIdx(String idx);
	UserNotice findByNoticeTitle(String title);
}
