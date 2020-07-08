package com.competition.jpa.repository.logger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.logger.Logger;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
	List<Logger> findByLevel(String level);
	List<Logger> findByLogger(String logger);
}
