package com.mercury.service.mileage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.mileage.MileageRequest;
import com.mercury.process.mileage.MileageRequestProcess;

/**
 * 
 * 마일리지 요청 관련 Service
 * 
 * @author nkt
 *
 *
 *         Create by User Date : 2020. 9. 1.
 *
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class MileageRequestService {

	@Autowired
	private MileageRequestProcess mileageRequestProcess;

	/**
	 * 
	 * 마일리지 출금 요청 승인
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T approvalMileage(String idx) throws Exception {
		return (T) mileageRequestProcess.approvalMileage(idx);
	}

	/**
	 * 마일리지 사용자 명으로 조회
	 * 
	 * @param <T>
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seMileageByUserName(String userName)
			throws Exception {
		return mileageRequestProcess.seMileageByUserName(userName);
	}

	/**
	 * 마일리지 목록 전체 조회
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seMileages() throws Exception {
		return mileageRequestProcess.seMileages();
	}

	/**
	 * 마일리지 일렬번호로 조회
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seMileage(String idx) throws Exception {
		return mileageRequestProcess.seMileage(idx);
	}

	/**
	 * 
	 * 마일리지 출금 요청
	 * 
	 * @param <T>
	 * @param mileage
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T requestMileage(MileageRequest mileage)
			throws Exception {
		return mileageRequestProcess.requestMileage(mileage);
	}

	/**
	 * 마일리지 요청 이력 삭제
	 * 
	 * @param <T>
	 * @param mileage
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T deMileageAllEntities(
			List<MileageRequest> mileage) throws Exception {
		return mileageRequestProcess.deMileageAllEntities(mileage);
	}
}
