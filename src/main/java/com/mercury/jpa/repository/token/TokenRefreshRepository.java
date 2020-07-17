package com.mercury.jpa.repository.token;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.token.TokenRefresh;

@Repository
public interface TokenRefreshRepository extends JpaRepository<TokenRefresh, Long>{
	TokenRefresh findByToken(String token);
	List<TokenRefresh> findByUserName(String username);
}
