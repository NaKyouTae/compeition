package com.competition.jpa.repository.token.black;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.token.TokenBlack;

@Repository
public interface BlackTokenRepository extends JpaRepository<TokenBlack, Long>{
	TokenBlack findByToken(String token);
}
