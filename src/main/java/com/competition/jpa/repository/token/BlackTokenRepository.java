package com.competition.jpa.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.token.BlackToken;

@Repository
public interface BlackTokenRepository extends JpaRepository<BlackToken, Long>{
	BlackToken findByToken(String token);
}
