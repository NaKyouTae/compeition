package com.competition.jpa.repository.system.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.system.config.SystemConfig;

@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {
	SystemConfig findByConfigName(String name);
	SystemConfig findByConfigType(String type);
	SystemConfig findByConfigValue(String value);
}
