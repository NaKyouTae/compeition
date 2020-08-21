package com.mercury.service.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.process.system.config.SystemConfigProcess;

@Service
@SuppressWarnings("unchecked")
public class SystemConfigService {
	
	@Autowired
	private SystemConfigProcess systemConfigProcess;
	
	
	public <T extends Object> T seSystemConfigs() throws Exception {
		try {
			return (T) systemConfigProcess.seSystemConfigs();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seSystemConfigByConfigName(String name) throws Exception {
		try {
			return (T) systemConfigProcess.seSystemConfigByConfigName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seSystemConfigByConfigType(String type) throws Exception {
		try {
			return (T) systemConfigProcess.seSystemConfigByConfigType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seSystemConfigByConfigValue(String value) throws Exception {
		try {
			return (T) systemConfigProcess.seSystemConfigByConfigValue(value);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inSystemConfig(SystemConfig config) throws Exception {
		try {
			return (T) systemConfigProcess.inSystemConfig(config);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upSystemConfig(SystemConfig config) throws Exception {
		try {
			return (T) systemConfigProcess.upSystemConfig(config);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deSystemConfig(SystemConfig config) throws Exception {
		try {
			return (T) systemConfigProcess.deSystemConfig(config);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
}
