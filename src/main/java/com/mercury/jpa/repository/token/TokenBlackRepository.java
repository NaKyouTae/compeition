package com.mercury.jpa.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.token.TokenBlack;

@Repository
public interface TokenBlackRepository extends JpaRepository<TokenBlack, Long>{
	TokenBlack findByToken(String token);
}
