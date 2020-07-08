package com.competition.process.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.logger.Logger;
import com.competition.jpa.repository.logger.LoggerRepository;

@Component
@SuppressWarnings("unchecked")
public class LoggerProcess {

	@Autowired
	private LoggerRepository loggerRepository;
	
	public <T extends Object> T seLoggerAll() throws Exception {
		try {
			return (T) loggerRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public <T extends Object> T deLogger(Logger logger) throws Exception {
		try {
			loggerRepository.delete(logger);
			return (T) Boolean.TRUE; 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
