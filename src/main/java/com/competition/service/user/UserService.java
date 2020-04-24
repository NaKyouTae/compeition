package com.competition.service.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.User;
import com.competition.jpa.model.UserMappingRole;
import com.competition.jpa.repository.UserMappingRoleRepository;
import com.competition.jpa.repository.UserRepository;
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
	private UserMappingRoleRepository userMappingRoleRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		
		List<UserMappingRole> roles = userMappingRoleRepository.findByUserName(username);
		
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
