package com.competition.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class AppRunner implements CommandLineRunner{
	
	@Autowired
	Environment env;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		env.getProperty("spring.data.maria.username", "root");
	}
	

}
