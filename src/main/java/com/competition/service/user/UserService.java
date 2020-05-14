package com.competition.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserRole;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.user.UserRoleRepository;
import com.competition.process.user.UserProcess;
import com.competition.user.CustomUserDetails;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private UserProcess userProcess;
	
	@Autowired
	private UserRoleRepository userMappingRoleRepository; 
	
	
	public <T extends Object> T checkUserName(String userName) throws Exception {
		try {
			
			CustomUserDetails user = (CustomUserDetails) loadUserByUsername(userName);
			
			if(user != null) return (T) Boolean.TRUE;
			
			return (T) Boolean.FALSE;
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		List<UserRole> roles = userMappingRoleRepository.findByUserName(username);
		
		CustomUserDetails ud = new CustomUserDetails();
		ud.setUser(user);
		ud.setRoles(roles);
		return ud;
	}
	
	public <T extends Object> T getLists() throws Exception {
		return (T) userProcess.getLists();
	}
	
	public <T extends Object> T signUp(User user) throws Exception {
		
		user.setIdx(UUID.randomUUID().toString().replace("-", ""));
		user.setInsertDate(DateUtil.now());
		
		return (T) userProcess.signUp(user);
	}
	public <T extends Object> T updateUser(User user) throws Exception {
		try {
			user.setChangeDate(DateUtil.now());
			return (T) userProcess.updateUser(user); 
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
