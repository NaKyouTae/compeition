package com.mercury.jpa.repository.cash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.cash.Cash;
@Repository
public interface CashRepository extends JpaRepository<Cash, Long>{
	Cash findByIdx(String idx);
	List<Cash> findByUserName(String userName);
	List<Cash> findByApproval(String yn);
}


