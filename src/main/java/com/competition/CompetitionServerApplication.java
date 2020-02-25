package com.competition;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.jpa.model.Role;
import com.competition.jpa.model.User;
import com.competition.jpa.model.UserMappingRole;
import com.competition.jpa.repository.RoleRepository;
import com.competition.jpa.repository.UserMappingRoleRepository;
import com.competition.jpa.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CompetitionServerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:4300", "http://localhost:8080")
        .allowCredentials(true).maxAge(3600);
    }
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public CommandLineRunner runner(UserRepository user, UserMappingRoleRepository mapping_role, RoleRepository role) {
		return (args) -> {
			{
				user.deleteAll();
				
				User u = new User();
				u.setUsername("test");
				u.setPassword(passwordEncoder.encode("test"));
				u.setInsert_date(LocalDateTime.now());
				u.setChange_date(null);
				user.save(u);
			}
			
			{
				role.deleteAll();
				
				Role admin_role = new Role();
				admin_role.setRolename("ROLE_ADMIN");
				admin_role.setInsert_date(LocalDateTime.now());
				admin_role.setChange_date(null);
				role.save(admin_role);

				Role user_role = new Role();
				user_role.setRolename("ROLE_USER");
				user_role.setInsert_date(LocalDateTime.now());
				user_role.setChange_date(null);
				role.save(user_role);
			}
			
			{
				mapping_role.deleteAll();
				
				UserMappingRole mapping = new UserMappingRole();
				mapping.setRolename("ROLE_ADMIN");
				mapping.setUsername("test");
				mapping_role.save(mapping);
			}
			
		};
    } 
}
