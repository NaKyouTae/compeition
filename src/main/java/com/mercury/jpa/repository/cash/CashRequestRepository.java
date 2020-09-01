package com.mercury.jpa.repository.cash;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.cash.CashRequest;
/**
 * 마일리지 요청 관련 Repository
 *  
 * @author nkt
 *
 *
 * Create by User Date : 2020. 9. 1.
 *
 */
@Repository
public interface CashRequestRepository extends JpaRepository<CashRequest, Long>{
	CashRequest findByIdx(String idx);
	List<CashRequest> findByUserName(String userName);
	List<CashRequest> findByApproval(Boolean approval);
}


