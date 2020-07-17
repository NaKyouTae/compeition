package com.mercury.controller.logger;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.logger.LoggerEntity;
import com.mercury.service.logger.LoggerService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/loggers")
public class LoggerController {
	
	private static final Logger LOGGER = LogManager.getLogger(LoggerController.class);
	
	@Autowired
	private LoggerService loggerService;
	
	@GetMapping("")
	public <T extends Object> T seLoggerAll() throws Exception {
		ControllerResponse<List<LoggerEntity>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Logger  List :) ");
			res.setResult(loggerService.seLoggerAll());
		} catch (Exception e) {
			LOGGER.error(e);
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/loggers")
	public <T extends Object> T seLoggers(String logger) throws Exception {
		ControllerResponse<List<LoggerEntity>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Column Logger List :) ");
			res.setResult(loggerService.seLoggers(logger));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/levels")
	public <T extends Object> T seLevels(String level) throws Exception {
		ControllerResponse<List<LoggerEntity>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Column Level List :) ");
			res.setResult(loggerService.seLevels(level));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@DeleteMapping("")
	public <T extends Object> T deLogger(@RequestBody LoggerEntity logger) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Logger :) ");
			res.setResult(loggerService.deLogger(logger));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
}
