package com.mercury.jpa.repository.logger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.logger.LoggerEntity;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
	List<LoggerEntity> findByLevel(String level);
	List<LoggerEntity> findByLogger(String logger);
}
