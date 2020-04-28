package com.competition.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserRole;

import lombok.Data;


@Data
public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -6772471990977901460L;

	private User user;
	private List<UserRole> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		
		for(UserRole r : roles) {			
			auth.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		
		return auth;
	}

	@Override
	public String getPassword() {
		if(user == null) return null;
		return user.getPw();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
