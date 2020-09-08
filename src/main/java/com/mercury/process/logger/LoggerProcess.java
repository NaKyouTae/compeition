package com.mercury.process.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.controller.logger.LoggerController;
import com.mercury.jpa.model.logger.LoggerEntity;
import com.mercury.jpa.repository.logger.LoggerRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class LoggerProcess {
	private static final Logger LOGGER = LogManager
			.getLogger(LoggerController.class);
	@Autowired
	private LoggerRepository loggerRepository;

	public <T extends Object> T seLoggerAll() throws Exception {
		return (T) loggerRepository.findAll();
	}

	public <T extends Object> T seLoggers(String logger) throws Exception {
		return (T) loggerRepository.findByLogger(logger);
	}

	public <T extends Object> T seLevels(String level) throws Exception {
		return (T) loggerRepository.findByLevel(level);
	}

	public <T extends Object> T deLogger(LoggerEntity logger) throws Exception {
		loggerRepository.delete(logger);
		return (T) Boolean.TRUE;
	}
}
