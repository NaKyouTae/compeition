package com.competition.process.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.user.User;
import com.competition.jpa.repository.user.UserRepository;

@Component
@SuppressWarnings("unchecked")
public class UserProcess {
	@Autowired
	private UserRepository userRepository;
	
	public <T extends Object> T getLists() throws Exception {
		return (T) userRepository.findAll();
	}
	
	public <T extends Object> T signUp(User user) throws Exception {
		return (T) userRepository.save(user);
	}
	
	public <T extends Object> T updateUser(User user) throws Exception {
		return (T) userRepository.save(user);
	}
	
	public void destoryUser(User user) throws Exception {
		userRepository.delete(user);
	}
}
