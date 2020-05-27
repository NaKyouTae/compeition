package com.competition.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.grade.Grade;
import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserGrade;
import com.competition.jpa.model.user.UserRole;
import com.competition.jpa.repository.user.UserGradeRepository;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.user.UserRoleRepository;
import com.competition.process.user.UserProcess;
import com.competition.service.grade.GradeService;
import com.competition.user.CustomUserDetails;
import com.competition.util.DateUtil;
import com.competition.util.ObjectUtil;

@Service
@SuppressWarnings("unchecked")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private UserProcess userProcess;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserGradeRepository userGradeRepository;
	
	@Autowired
	private GradeService gradeService;
	
	
	public <T extends Object> T checkUserName(String userName) throws Exception {
		try {
			
			CustomUserDetails user = (CustomUserDetails) loadUserByUsername(userName);
			
			if(user.getUser() != null) return (T) Boolean.TRUE;
			
			return (T) Boolean.FALSE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		List<UserRole> roles = userRoleRepository.findByUserName(username);
		UserGrade grade = userGradeRepository.findByUserName(username);
		
		CustomUserDetails ud = new CustomUserDetails();
		ud.setUser(user);
		ud.setRoles(roles);
		ud.setGrade(grade);
		return ud;
	}
	
	public <T extends Object> T getLists() throws Exception {
		return (T) userProcess.getLists();
	}
	
	public <T extends Object> T signUp(User user) throws Exception {
		try {
			String userIdx = UUID.randomUUID().toString().replace("-", ""); 
			user.setIdx(userIdx);
			user.setInsertDate(DateUtil.now());
			
			
			UserRole userRole = new UserRole();
			userRole.setIdx(UUID.randomUUID().toString().replace("-", ""));
			userRole.setUserIdx(userIdx);
			userRole.setUserName(user.getUsername());
			userRole.setRoleIdx(null);
			userRole.setRoleName(null);
			
			Grade gradeInfo = gradeService.seGrade("RED");
			
			UserGrade userGrade = new UserGrade();
			userGrade.setIdx(UUID.randomUUID().toString().replace("-", ""));
			userGrade.setUserIdx(userIdx);
			userGrade.setUserName(user.getUsername());
			userGrade.setGradeIdx(gradeInfo.getIdx());
			userGrade.setGradeName(gradeInfo.getGradeName());
			
			userRoleRepository.save(userRole);
			userGradeRepository.save(userGrade);
			
			return (T) userProcess.signUp(user);
		} catch (Exception e) {
			return (T) e;
		}
		
	}
	public <T extends Object> T upUser(User user, UserRole role) throws Exception {
		try {
			user.setChangeDate(DateUtil.now());
			
			if(role != null) {				
				UserRole userRole = userRoleRepository.findByRoleName(role.getRoleName());
				UserRole sumRole = ObjectUtil.toObject(role, userRole);
				userRoleRepository.save(sumRole);
			}
			
			return (T) userProcess.upUser(user); 
		}catch(Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T destoryUser(User user) throws Exception {
		try {
			userProcess.destoryUser(user);
			return (T) Boolean.TRUE;
		}catch(Exception e) {
			return (T) e;
		}
	}
}
