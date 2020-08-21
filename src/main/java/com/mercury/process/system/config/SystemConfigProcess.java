package com.mercury.process.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;

@Component
@SuppressWarnings("unchecked")
public class SystemConfigProcess {
	
	@Autowired
	private SystemConfigRepository systemConfigRepository;
	
	
	public <T extends Object> T seSystemConfigs() throws Exception {
		try {
			return (T) systemConfigRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seSystemConfigByConfigName(String name) throws Exception {
		try {
			return (T) systemConfigRepository.findByConfigName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seSystemConfigByConfigType(String type) throws Exception {
		try {
			return (T) systemConfigRepository.findByConfigType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seSystemConfigByConfigValue(String value) throws Exception {
		try {
			return (T) systemConfigRepository.findByConfigValue(value);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inSystemConfig(SystemConfig config) throws Exception {
		try {
			return (T) systemConfigRepository.save(config);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upSystemConfig(SystemConfig config) throws Exception {
		try {
			return (T) systemConfigRepository.save(config);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deSystemConfig(SystemConfig config) throws Exception {
		try {
			systemConfigRepository.delete(config);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
}
