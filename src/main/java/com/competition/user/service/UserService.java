package com.competition.user.service;

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
import com.competition.user.CustomUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	UserMappingRoleRepository userMappingRoleRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		List<UserMappingRole> roles = userMappingRoleRepository.findByUsername(username);
		
		CustomUserDetails ud = new CustomUserDetails();
		ud.setUser(user);
		ud.setRoles(roles);
		return ud;
	}
	
}
