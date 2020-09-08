package com.mercury.service.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.process.system.config.SystemConfigProcess;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SystemConfigService {

	@Autowired
	private SystemConfigProcess systemConfigProcess;

	public <T extends Object> T seSystemConfigs() throws Exception {
		return (T) systemConfigProcess.seSystemConfigs();
	}

	public <T extends Object> T seSystemConfigByConfigName(String name)
			throws Exception {
		return (T) systemConfigProcess.seSystemConfigByConfigName(name);
	}

	public <T extends Object> T seSystemConfigByConfigType(String type)
			throws Exception {
		return (T) systemConfigProcess.seSystemConfigByConfigType(type);
	}

	public <T extends Object> T seSystemConfigByConfigValue(String value)
			throws Exception {
		return (T) systemConfigProcess.seSystemConfigByConfigValue(value);
	}

	public <T extends Object> T inSystemConfig(SystemConfig config)
			throws Exception {
		return (T) systemConfigProcess.inSystemConfig(config);
	}

	public <T extends Object> T upSystemConfig(SystemConfig config)
			throws Exception {
		return (T) systemConfigProcess.upSystemConfig(config);
	}

	public <T extends Object> T deSystemConfig(SystemConfig config)
			throws Exception {
		return (T) systemConfigProcess.deSystemConfig(config);
	}

}
