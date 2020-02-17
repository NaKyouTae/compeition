package com.competition.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.User;
import com.competition.jpa.repository.UserRepository;
import com.competition.user.CustomUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		CustomUserDetails ud = new CustomUserDetails();
		ud.setUser(user);
		return ud;
	}
	
}
