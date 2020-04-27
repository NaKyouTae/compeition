package com.competition;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.jpa.model.Role;
import com.competition.jpa.model.User;
import com.competition.jpa.model.UserMappingRole;
import com.competition.jpa.repository.MenuRepository;
import com.competition.jpa.repository.RoleRepository;
import com.competition.jpa.repository.UserMappingRoleRepository;
import com.competition.jpa.repository.UserRepository;
import com.competition.util.DateUtil;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CompetitionServerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:4300", "http://localhost:8080")
        .allowedMethods("GET","POST", "PUT", "DELETE")
        .exposedHeaders("Access-JWT", "Refresh-JWT")
        .allowCredentials(true).maxAge(3600);
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new JwtInterceptor())
//				.addPathPatterns("/service/**")
//				.excludePathPatterns("/user/**");
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public CommandLineRunner runner(UserRepository user, UserMappingRoleRepository mapping_role, RoleRepository role, MenuRepository menu) {
		return (args) -> {
			{
				user.deleteAll();
				
				User u = new User();
				u.setIdx(UUID.randomUUID().toString());
				u.setUserName("admin");
				u.setPw(passwordEncoder.encode("skrbxo12!@"));
				u.setInsertDate(DateUtil.now());
				u.setChangeDate(null);
				user.save(u);
			}
			
			{
				role.deleteAll();
				
				Role admin_role = new Role();
				admin_role.setIdx(UUID.randomUUID().toString().replace("-", ""));
				admin_role.setRoleName("ROLE_ADMIN");
				admin_role.setInsertDate(DateUtil.now());
				admin_role.setChangeDate(null);
				role.save(admin_role);

				Role user_role = new Role();
				user_role.setIdx(UUID.randomUUID().toString().replace("-", ""));
				user_role.setRoleName("ROLE_USER");
				user_role.setInsertDate(DateUtil.now());
				user_role.setChangeDate(null);
				role.save(user_role);
			}
			
			{
				mapping_role.deleteAll();
				
				UserMappingRole mapping = new UserMappingRole();
				mapping.setRoleName("ROLE_ADMIN");
				mapping.setUserName("admin");
				mapping_role.save(mapping);
			}			
		};
    } 
}
