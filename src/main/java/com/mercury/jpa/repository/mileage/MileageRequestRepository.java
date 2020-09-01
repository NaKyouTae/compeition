package com.mercury.jpa.repository.mileage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.mileage.MileageRequest;
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
public interface MileageRequestRepository extends JpaRepository<MileageRequest, Long>{
	MileageRequest findByIdx(String idx);
	List<MileageRequest> findByUserName(String userName);
	List<MileageRequest> findByApproval(Boolean approval);
}


