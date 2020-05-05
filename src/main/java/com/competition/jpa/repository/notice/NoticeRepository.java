package com.competition.jpa.repository.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.notice.Notice;
import com.competition.jpa.model.role.Role;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	Role findByIdx(String idx);
}
