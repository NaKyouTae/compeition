package com.mercury.service.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.logger.LoggerEntity;
import com.mercury.process.logger.LoggerProcess;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class LoggerService {

	@Autowired
	private LoggerProcess loggerProcess;

	public <T extends Object> T seLoggerAll() throws Exception {
		return (T) loggerProcess.seLoggerAll();
	}

	public <T extends Object> T seLoggers(String logger) throws Exception {
		return (T) loggerProcess.seLoggers(logger);
	}

	public <T extends Object> T seLevels(String level) throws Exception {
		return (T) loggerProcess.seLevels(level);
	}

	public <T extends Object> T deLogger(LoggerEntity logger) throws Exception {
		return (T) loggerProcess.deLogger(logger);
	}
}
