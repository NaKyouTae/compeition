package com.competition;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.user.User;
import com.competition.user.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CompetitionServerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:4300")
        .allowCredentials(true).maxAge(3600);
    }
	
	@Bean
	public CommandLineRunner runner(UserRepository userRepository) {
		return (args) -> {
			User u = new User();
			u.setUsername("test");
			u.setPassword("test");
			u.setDate(LocalDateTime.now());
			
			userRepository.save(u);
		};
    } 
}
