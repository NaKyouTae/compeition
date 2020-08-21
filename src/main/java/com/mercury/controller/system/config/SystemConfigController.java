package com.mercury.controller.system.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.service.system.config.SystemConfigService;

@RestController
@RequestMapping("/service/system/config")
@SuppressWarnings("unchecked")
public class SystemConfigController {
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	
	@GetMapping
	public <T extends Object> T seSystemConfigs() throws Exception {
		ControllerResponse<List<SystemConfig>> res = new ControllerResponse<>();
		
		try {
			return (T) systemConfigService.seSystemConfigs();
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/name")
	public <T extends Object> T seSystemConfigByConfigName(String name) throws Exception {
		ControllerResponse<SystemConfig> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Search System Config List :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(systemConfigService.seSystemConfigByConfigName(name));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/type")
	public <T extends Object> T seSystemConfigByConfigType(String type) throws Exception {
		ControllerResponse<List<SystemConfig>> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Search System Config By Config Type :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(systemConfigService.seSystemConfigByConfigType(type));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/value")
	public <T extends Object> T seSystemConfigByConfigValue(String value) throws Exception {
		ControllerResponse<SystemConfig> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Search System Config By Config Value :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(systemConfigService.seSystemConfigByConfigValue(value));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping
	public <T extends Object> T inSystemConfig(@RequestBody SystemConfig config) throws Exception {
		ControllerResponse<SystemConfig> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Insert System Config :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(systemConfigService.inSystemConfig(config));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PutMapping
	public <T extends Object> T upSystemConfig(@RequestBody SystemConfig config) throws Exception {
		ControllerResponse<SystemConfig> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Update System Config :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(systemConfigService.upSystemConfig(config));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@DeleteMapping
	public <T extends Object> T deSystemConfig(@RequestBody SystemConfig config) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Delete System Config :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(systemConfigService.deSystemConfig(config));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
}
