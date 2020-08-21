package com.mercury.jpa.repository.system.config;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.system.config.SystemConfig;

@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {
	SystemConfig findByConfigName(String name);
	List<SystemConfig> findByConfigType(String type);
	SystemConfig findByConfigValue(String value);
}
