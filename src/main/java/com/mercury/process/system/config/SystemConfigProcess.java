package com.mercury.process.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class SystemConfigProcess {

	@Autowired
	private SystemConfigRepository systemConfigRepository;

	public <T extends Object> T seSystemConfigs() throws Exception {
		return (T) systemConfigRepository.findAll();
	}

	public <T extends Object> T seSystemConfigByConfigName(String name)
			throws Exception {
		return (T) systemConfigRepository.findByConfigName(name);
	}

	public <T extends Object> T seSystemConfigByConfigType(String type)
			throws Exception {
		return (T) systemConfigRepository.findByConfigType(type);
	}

	public <T extends Object> T seSystemConfigByConfigValue(String value)
			throws Exception {
		return (T) systemConfigRepository.findByConfigValue(value);
	}

	public <T extends Object> T inSystemConfig(SystemConfig config)
			throws Exception {
		return (T) systemConfigRepository.save(config);
	}

	public <T extends Object> T upSystemConfig(SystemConfig config)
			throws Exception {
		return (T) systemConfigRepository.save(config);
	}

	public <T extends Object> T deSystemConfig(SystemConfig config)
			throws Exception {
		systemConfigRepository.delete(config);
		return (T) Boolean.TRUE;
	}

}
