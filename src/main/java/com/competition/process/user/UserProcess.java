package com.competition.process.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.User;
import com.competition.jpa.repository.UserRepository;

@Component
@SuppressWarnings("unchecked")
public class UserProcess {
	@Autowired
	private UserRepository userRepository;
	
	public <T extends Object> T getLists() {
		return (T) userRepository.findAll();
	}
	
	public <T extends Object> T signUp(User user) {
		return (T) userRepository.save(user);
	}
	
	public void destoryUser(User user) {
		userRepository.delete(user);
	}
}
