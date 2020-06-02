package com.competition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class AppRunner implements ApplicationRunner{
	
	@Autowired
	Environment env;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
	}

}
