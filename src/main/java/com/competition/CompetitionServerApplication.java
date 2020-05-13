package com.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.config.interceptor.JwtInterceptor;
import com.competition.jpa.repository.menu.MenuRepository;
import com.competition.jpa.repository.role.RoleRepository;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.user.UserRoleRepository;
import com.competition.service.user.UserService;

@SpringBootApplication
public class CompetitionServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4300", "http://localhost:8080")
				.allowedMethods("GET", "POST", "PUT", "DELETE").exposedHeaders("Access-JWT", "Refresh-JWT")
				.allowCredentials(true).maxAge(3600);
	}

	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**");
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	@Bean
	public CommandLineRunner runner(UserRepository user, UserRoleRepository mapping_role, RoleRepository role,
			MenuRepository menu) {
		return (args) -> {
//			{
//				user.deleteAll();
//
//				User u = new User();
//				u.setIdx(UUID.randomUUID().toString());
//				u.setUsername("admin");
//				u.setPassword(passwordEncoder.encode("skrbxo12!@"));
//				u.setInsertDate(DateUtil.now());
//				u.setChangeDate(null);
//				u.setEmail("kyoutae_93@naver.com");
//				user.save(u);
//
//				User u1 = new User();
//				u1.setIdx(UUID.randomUUID().toString());
//				u1.setUsername("test");
//				u1.setPassword(passwordEncoder.encode("test"));
//				u1.setInsertDate(DateUtil.now());
//				u1.setChangeDate(null);
//				u1.setEmail("qppk@naver.com");
//				user.save(u1);
//			}

//			{
//				role.deleteAll();
//
//				Role admin_role = new Role();
//				admin_role.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				admin_role.setRoleName("ROLE_ADMIN");
//				admin_role.setInsertDate(DateUtil.now());
//				admin_role.setChangeDate(null);
//				role.save(admin_role);
//			}

//			{
//				mapping_role.deleteAll();
//
//				User userInfo = user.findByUsername("admin");
//				UserRole mapping = new UserRole();
//
//				Role roleInfo = role.findByRoleName("ROLE_ADMIN");
//
//				mapping.setRoleIdx(roleInfo.getIdx());
//				mapping.setRoleName("ROLE_ADMIN");
//				mapping.setUserIdx(userInfo.getIdx());
//				mapping.setUserName("admin");
//
//				mapping_role.save(mapping);
//			}

//			{
//
//				List<Menu> m_list = new ArrayList<>();
//
//				Menu m1 = new Menu();
//
//				m1.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m1.setTitle("삼행시");
//				m1.setMenuGroup("three");
//				m1.setUrl("/three");
//				m1.setMenuOrder(1);
//				m1.setLevel(1);
//				m1.setChild(Boolean.FALSE);
//				m1.setInsertDate(DateUtil.now());
//				m1.setParent("null");
//				m1.setRoleIdx(null);
//				m1.setRoleIdx(null);
//
//				Menu m2 = new Menu();
//
//				m2.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m2.setTitle("이행시");
//				m2.setMenuGroup("two");
//				m2.setUrl("/two");
//				m2.setMenuOrder(2);
//				m2.setLevel(1);
//				m2.setChild(Boolean.FALSE);
//				m2.setInsertDate(DateUtil.now());
//				m2.setParent("null");
//				m2.setRoleIdx(null);
//				m2.setRoleIdx(null);
//
//				Menu m3 = new Menu();
//
//				m3.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m3.setTitle("명예의 전당");
//				m3.setMenuGroup("honor");
//				m3.setUrl("/honor");
//				m3.setMenuOrder(3);
//				m3.setLevel(1);
//				m3.setChild(Boolean.FALSE);
//				m3.setInsertDate(DateUtil.now());
//				m3.setParent("null");
//				m3.setRoleIdx(null);
//				m3.setRoleIdx(null);
//
//				Menu m4 = new Menu();
//
//				m4.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m4.setTitle("공지 사항");
//				m4.setMenuGroup("honor");
//				m4.setUrl("/honor");
//				m4.setMenuOrder(4);
//				m4.setLevel(1);
//				m4.setChild(Boolean.FALSE);
//				m4.setInsertDate(DateUtil.now());
//				m4.setParent("null");
//				m4.setRoleIdx(null);
//				m4.setRoleIdx(null);
//
//				Menu m5 = new Menu();
//
//				m5.setIdx("adminIdx");
//				m5.setTitle("관리자");
//				m5.setMenuGroup("admin");
//				m5.setUrl("/admin");
//				m5.setMenuOrder(5);
//				m5.setLevel(1);
//				m5.setChild(Boolean.FALSE);
//				m5.setInsertDate(DateUtil.now());
//				m5.setParent("null");
//				m5.setRoleIdx(null);
//				m5.setRoleIdx(null);
//
//				Menu m5_1 = new Menu();
//
//				m5_1.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m5_1.setTitle("메뉴");
//				m5_1.setMenuGroup("menu");
//				m5_1.setUrl("/admin/menu");
//				m5_1.setMenuOrder(1);
//				m5_1.setLevel(2);
//				m5_1.setChild(Boolean.FALSE);
//				m5_1.setInsertDate(DateUtil.now());
//				m5_1.setParent("adminIdx");
//				m5_1.setRoleIdx(null);
//				m5_1.setRoleIdx(null);
//
//				Menu m5_2 = new Menu();
//
//				m5_2.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m5_2.setTitle("사용자");
//				m5_2.setMenuGroup("user");
//				m5_2.setUrl("/admin/user");
//				m5_2.setMenuOrder(2);
//				m5_2.setLevel(2);
//				m5_2.setChild(Boolean.FALSE);
//				m5_2.setInsertDate(DateUtil.now());
//				m5_2.setParent("adminIdx");
//				m5_2.setRoleIdx(null);
//				m5_2.setRoleIdx(null);
//
//				Menu m5_3 = new Menu();
//
//				m5_3.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m5_3.setTitle("제시어");
//				m5_3.setMenuGroup("weekword");
//				m5_3.setUrl("/admin/weekword");
//				m5_3.setMenuOrder(3);
//				m5_3.setLevel(2);
//				m5_3.setChild(Boolean.FALSE);
//				m5_3.setInsertDate(DateUtil.now());
//				m5_3.setParent("adminIdx");
//				m5_3.setRoleIdx(null);
//				m5_3.setRoleIdx(null);
//
//				Menu m5_4 = new Menu();
//
//				m5_4.setIdx(UUID.randomUUID().toString().replace("-", ""));
//				m5_4.setTitle("권한");
//				m5_4.setMenuGroup("role");
//				m5_4.setUrl("/admin/role");
//				m5_4.setMenuOrder(4);
//				m5_4.setLevel(2);
//				m5_4.setChild(Boolean.FALSE);
//				m5_4.setInsertDate(DateUtil.now());
//				m5_4.setParent("adminIdx");
//				m5_4.setRoleIdx(null);
//				m5_4.setRoleIdx(null);
//			}
		};
	}
}
