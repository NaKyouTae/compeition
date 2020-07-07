package com.competition.jpa.repository.logger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.logger.JpaLogger;

@Repository
public interface JpaLogRepository extends JpaRepository<JpaLogger, Long> {
	JpaLogger findByLevel(String level);
}
