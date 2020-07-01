package com.competition.jpa.repository.token.refresh;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.token.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
	RefreshToken findByToken(String token);
	List<RefreshToken> findByUserName(String username);
}
