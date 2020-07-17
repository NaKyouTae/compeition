package com.mercury.user;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationToken {
	private String username;
    private Collection authorities;
    private String token;
}
