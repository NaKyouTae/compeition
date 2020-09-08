package com.mercury.process.mileage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.mercury.jpa.model.mileage.MileageRequest;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.mileage.MileageRequestRepository;
import com.mercury.service.mail.MailService;
import com.mercury.service.user.UserService;
import com.mercury.user.CustomUserDetails;
import com.mercury.util.DateUtil;
import com.mercury.util.ObjectUtil;
import com.mercury.util.UUIDUtil;

/**
 * 마일리지 요청 관련 Process
 * 
 * @author nkt
 *
 *
 * Create by User Date : 2020. 9. 1.
 *
 */
@Component
@Transactional
@SuppressWarnings("unchecked")
public class MileageRequestProcess {
	
	@Autowired
	private MileageRequestRepository mileageRequestRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	
	/**
	 * 마일리지 출금 요청 승인
	 * 승인시 승인 메일 발송
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T approvalMileage(String idx) throws Exception {
		MileageRequest mileageRequest = mileageRequestRepository.findByIdx(idx);
		MileageRequest apMileage = ObjectUtil.toObj(mileageRequest, new MileageRequest());
		
		CustomUserDetails customUser = (CustomUserDetails) userService.loadUserByUsername(mileageRequest.getUserName());
		User user = customUser.getUser();
		
		apMileage.setApproval(Boolean.TRUE);
		apMileage.setPaymentDate(DateUtil.now());
		
		// 마일리지 요청 승인
		mileageRequestRepository.save(apMileage);
		
		// 이메일 전송
		mailService.approvalWithdraw(user.getEmail());
		
		return (T) Boolean.TRUE; 
	}
	
	/**
	 * 마일리지 사용자 명으로 조회
	 * 
	 * @param <T>
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seMileageByUserName(String userName) throws Exception {
		return (T) mileageRequestRepository.findByUserName(userName);
	}
	
	/**
	 * 마일리지 목록 전체 조회
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seMileages() throws Exception {
		return (T) mileageRequestRepository.findAll();
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
		return (T) mileageRequestRepository.findByIdx(idx);
	}
	
	/**
	 * 마일리지 출금 요청
	 * 출금 요청시 요청 메일 발송
	 * 
	 * @param <T>
	 * @param cr
	 * @return
	 * @throws Exception
	 */
	
	public <T extends Object> T requestMileage(MileageRequest mileage) throws Exception {
		System.out.println("mileage process" + TransactionSynchronizationManager.getCurrentTransactionName());
		
		CustomUserDetails customUser = (CustomUserDetails) userService.loadUserByUsername(mileage.getUserName());
		User user = customUser.getUser();
		
		mileage.setIdx(UUIDUtil.randomString());
		mileage.setWithDrawDate(DateUtil.now());
		mileage.setApproval(Boolean.FALSE);
		mileage.setPrevMileage(user.getMileage());
		
		user.setMileage(user.getMileage() - mileage.getWithDrawMileage());
		
		// 사용자 정보 중 마일리지 정보 수정
		userService.upUser(user, null);
		// 요청 정보 생성
		mileageRequestRepository.save(mileage);
		// 이메일 전송
		mailService.requestWithdraw(user.getEmail());
		return (T) Boolean.TRUE;
	}
	
	/**
	 * 마일리지 요청 이력 삭제
	 * 
	 * @param <T>
	 * @param mileage
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T deMileageAllEntities(List<MileageRequest> mileage) throws Exception {
		mileageRequestRepository.deleteAll(mileage);
		return (T) Boolean.TRUE;
	}
}
