package com.competition.service.user;

import java.util.List;

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
		User user = userRepository.findByUsername(username);
		
		List<UserMappingRole> roles = userMappingRoleRepository.findByUsername(username);
		
		CustomUserDetails ud = new CustomUserDetails();
		ud.setUser(user);
		ud.setRoles(roles);
		return ud;
	}
	
	public <T extends Object> T getLists() {
		return (T) userProcess.getLists();
	}
}
