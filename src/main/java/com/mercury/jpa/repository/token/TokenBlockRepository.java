package com.mercury.jpa.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.token.TokenBlock;

@Repository
public interface TokenBlockRepository extends JpaRepository<TokenBlock, Long>{
	TokenBlock findByToken(String token);
}
