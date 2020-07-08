package com.competition.service.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.logger.Logger;
import com.competition.process.logger.LoggerProcess;

@Service
@SuppressWarnings("unchecked")
public class LoggerService {
	
	@Autowired
	private LoggerProcess loggerProcess;
	
	public <T extends Object> T seLoggerAll() throws Exception {
		try {
			return (T) loggerProcess.seLoggerAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoggers(String logger) throws Exception {
		try {
			return (T) loggerProcess.seLoggers(logger);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLevels(String level) throws Exception {
		try {
			return (T) loggerProcess.seLevels(level);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deLogger(Logger logger) throws Exception {
		try {
			return (T) loggerProcess.deLogger(logger); 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
