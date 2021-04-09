package com.mercury.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.mail")
public class PropertiesConfig {
	private String username;
	private String password;
	private String host;
	private String port;
	private String protocol;
}
