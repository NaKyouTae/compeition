package com.competition.jpa.repository.token.refresh;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.token.TokenRefresh;

@Repository
public interface RefreshTokenRepository extends JpaRepository<TokenRefresh, Long>{
	TokenRefresh findByToken(String token);
	List<TokenRefresh> findByUserName(String username);
}
