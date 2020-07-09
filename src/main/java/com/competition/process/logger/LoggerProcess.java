package com.competition.process.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.controller.logger.LoggerController;
import com.competition.jpa.model.logger.LoggerEntity;
import com.competition.jpa.repository.logger.LoggerRepository;

@Component
@SuppressWarnings("unchecked")
public class LoggerProcess {
	private static final Logger LOGGER = LogManager.getLogger(LoggerController.class);
	@Autowired
	private LoggerRepository loggerRepository;
	
	public <T extends Object> T seLoggerAll() throws Exception {
		try {
			return (T) loggerRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoggers(String logger) throws Exception {
		try {
			return (T) loggerRepository.findByLogger(logger);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLevels(String level) throws Exception {
		try {
			return (T) loggerRepository.findByLevel(level);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deLogger(LoggerEntity logger) throws Exception {
		try {
			loggerRepository.delete(logger);
			return (T) Boolean.TRUE; 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
