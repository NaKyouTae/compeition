package com.mercury;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mercury.common.interceptor.JwtInterceptor;
import com.mercury.jpa.model.grade.Grade;
import com.mercury.jpa.model.menu.Menu;
import com.mercury.jpa.model.role.Role;
import com.mercury.jpa.model.system.config.SystemConfig;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.user.UserGrade;
import com.mercury.jpa.model.user.UserRole;
import com.mercury.jpa.repository.grade.GradeRepository;
import com.mercury.jpa.repository.menu.MenuRepository;
import com.mercury.jpa.repository.role.RoleRepository;
import com.mercury.jpa.repository.system.config.SystemConfigRepository;
import com.mercury.jpa.repository.user.UserGradeRepository;
import com.mercury.jpa.repository.user.UserRepository;
import com.mercury.jpa.repository.user.UserRoleRepository;
import com.mercury.util.DateUtil;
import com.mercury.util.UUIDUtil;

@ServletComponentScan
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MercuryServerApplication implements WebMvcConfigurer {

	private static final Logger LOGGER = LogManager.getLogger(MercuryServerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MercuryServerApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4300", "http://localhost:8090", "http://127.0.0.1:4300", "http://127.0.0.1:8090")
				.allowedMethods("GET", "POST", "PUT", "DELETE").exposedHeaders("AWT", "RWT", "UWT")
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

	@Bean
	public CommandLineRunner runner(UserRepository user, UserRoleRepository mapping_role, RoleRepository role,
			MenuRepository menu, GradeRepository grade, UserGradeRepository user_grade, SystemConfigRepository sysconf) {
		return (args) -> {
			{
				List<SystemConfig> syslist = sysconf.findAll();
				if(syslist.size() == 0) {
					
					
					long awtExp = 1 * (1000 * 60 * 5);
					long jwtExp = 7 * (1000 * 60 * 60 * 24);
					
					sysconf.saveAll(Arrays.asList(
							// JWT SECRET
							SystemConfig.builder().configType("JWT").configName("RWT_SECRET").configValue("MercuryRefreshjwt").build(),
							SystemConfig.builder().configType("JWT").configName("AWT_SECRET").configValue("MercuryAccessjwt").build(),
							SystemConfig.builder().configType("JWT").configName("UWT_SECRET").configValue("MercuryUserjwt").build(),
							// JWT ISSUER
							SystemConfig.builder().configType("JWT").configName("ISSUER").configValue("Mercury").build(),
							// JWT SUBJECT
							SystemConfig.builder().configType("JWT").configName("RWT_SUBJECT").configValue("REFRESH").build(),
							SystemConfig.builder().configType("JWT").configName("AWT_SUBJECT").configValue("ACCESS").build(),
							SystemConfig.builder().configType("JWT").configName("UWT_SUBJECT").configValue("USER").build(),
							// JWT TYPE
							SystemConfig.builder().configType("JWT").configName("RWT_TYPE").configValue("REFRESHJWT").build(),
							SystemConfig.builder().configType("JWT").configName("AWT_TYPE").configValue("ACCESSJWT").build(),
							SystemConfig.builder().configType("JWT").configName("UWT_TYPE").configValue("USERJWT").build(),
							// JWT EXPRIATION
							SystemConfig.builder().configType("JWT").configName("RWT_EXPRIATION").configValue(String.valueOf(jwtExp)).build(),
							SystemConfig.builder().configType("JWT").configName("AWT_EXPRIATION").configValue(String.valueOf(awtExp)).build(),
							SystemConfig.builder().configType("JWT").configName("UWT_EXPRIATION").configValue(String.valueOf(jwtExp)).build(),
							// MAIL INFOMATION
							SystemConfig.builder().configType("MAIL").configName("MAIL_ADDRESS").configValue("qppk123@gmail.com").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_PASSWORD").configValue("skrbxo12").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_PORT").configValue("587").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_HOST").configValue("smtp.gmail.com").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_SMTP_PROTOCOL").configValue("smtp").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_SMTP_AUTH").configValue("true").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_SMTP_STARTTLS_ENABLE").configValue("true").build(),
							SystemConfig.builder().configType("MAIL").configName("MAIL_DEBUG").configValue("true").build()
							// MAIL TEMPLATE REQURIED
					));
					
				}
				
				
			}
			
			{
				List<Grade> grades = grade.findAll();
				
				if(grades.size() == 0) {
					
					grade.saveAll(Arrays.asList(
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(1).startRange("0").endRange("999").idx("RED").gradeName("빨강색").build(),
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(2).startRange("1000").endRange("2999").idx("ORANGE").gradeName("주황색").build(),
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(3).startRange("2000").endRange("3999").idx("YELLOW").gradeName("노랑색").build(),
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(4).startRange("3000").endRange("4999").idx("GREEN").gradeName("초록색").build(),
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(5).startRange("4000").endRange("5999").idx("BLUE").gradeName("파랑색").build(),
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(6).startRange("5000").endRange("6999").idx("NAVY").gradeName("남색").build(),
						Grade.builder().insertDate(DateUtil.now()).gradeOrder(7).startRange("6000").endRange("7000").idx("PURPLE").gradeName("보라색").build()
					));
				}
			}
			
			{
				List<User> users = user.findAll();
				if(users.size() == 0) {
					String a = UUIDUtil.randomString();
					String t = UUIDUtil.randomString();
					
					user.saveAll(Arrays.asList(
						User.builder().idx(a).insertDate(DateUtil.now()).changeDate(null).mileage(0).sns("DEFAULT").username("admin").password(passwordEncoder.encode("skrbxo12!@")).email("qppk123@gmail.com").build(),
						User.builder().idx(t).insertDate(DateUtil.now()).changeDate(null).mileage(0).sns("DEFAULT").username("test").password(passwordEncoder.encode("test")).email("qppk123@gmail.com").build()
					));
					
					String a_idx = UUIDUtil.randomString();
					String u_idx = UUIDUtil.randomString();
					
					role.saveAll(Arrays.asList(
						Role.builder().idx(a_idx).insertDate(DateUtil.now()).changeDate(null).roleName("ROLE_ADMIN").build(),
						Role.builder().idx(u_idx).insertDate(DateUtil.now()).changeDate(null).roleName("ROLE_USER").build()
					));
					
					mapping_role.saveAll(Arrays.asList(
						UserRole.builder().idx(UUIDUtil.randomString()).roleIdx(a_idx).userIdx(a).roleName("ROLE_ADMIN").userName("admin").build(),
						UserRole.builder().idx(UUIDUtil.randomString()).roleIdx(a_idx).userIdx(t).roleName("ROLE_USER").userName("test").build()
					));
					
					Grade red = grade.findByIdx("RED");
					
					user_grade.saveAll(Arrays.asList(
						UserGrade.builder().idx(UUIDUtil.randomString()).userIdx(a).userName("admin").gradeIdx(red.getIdx()).gradeName(red.getGradeName()).build(),
						UserGrade.builder().idx(UUIDUtil.randomString()).userIdx(t).userName("test").gradeIdx(red.getIdx()).gradeName(red.getGradeName()).build()
					));
					
				}
			}

			{
				List<Menu> menus = menu.findAll();
				
				if(menus.size() == 0) {
					
					String m_admin = UUIDUtil.randomString();
					
					Role r_admin = role.findByRoleName("ROLE_ADMIN");
					
					menu.saveAll(Arrays.asList(
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(1).level(1).insertDate(DateUtil.now()).parent("null").roleIdx(null).roleTitle(null).child(Boolean.FALSE).title("삼행시").menuGroup("three").url("/three").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(2).level(1).insertDate(DateUtil.now()).parent("null").roleIdx(null).roleTitle(null).child(Boolean.FALSE).title("이행시").menuGroup("two").url("/two").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(3).level(1).insertDate(DateUtil.now()).parent("null").roleIdx(null).roleTitle(null).child(Boolean.FALSE).title("명예의 전당").menuGroup("honor").url("/honor").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(4).level(1).insertDate(DateUtil.now()).parent("null").roleIdx(null).roleTitle(null).child(Boolean.FALSE).title("공지 사항").menuGroup("notice").url("/notice").build(),
						Menu.builder().idx(m_admin).menuOrder(5).level(1).insertDate(DateUtil.now()).parent("null").roleIdx(null).roleTitle(null).child(Boolean.FALSE).title("관리자").menuGroup("admin").url("/admin").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(1).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("메뉴").menuGroup("menu").url("/admin/menu").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(2).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("사용자").menuGroup("user").url("/admin/user").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(3).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("제시어").menuGroup("weekword").url("/admin/weekword").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(4).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("권한").menuGroup("role").url("/admin/role").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(5).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("등급").menuGroup("grade").url("/admin/grade").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(6).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("메일").menuGroup("mail").url("/admin/mail").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(7).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("접속 이력").menuGroup("login").url("/admin/login").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(8).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("설정").menuGroup("config").url("/admin/config").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(9).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("공지사항").menuGroup("notice").url("/admin/notice").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(10).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("Logs").menuGroup("log").url("/admin/log").build(),
						Menu.builder().idx(UUIDUtil.randomString()).menuOrder(11).level(2).insertDate(DateUtil.now()).parent(m_admin).roleIdx(r_admin.getIdx()).roleTitle(r_admin.getRoleName()).child(Boolean.FALSE).title("출금").menuGroup("cash").url("/admin/cash").build()
					));
				}
			}
		};
	}
}
