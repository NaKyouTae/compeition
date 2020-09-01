package com.mercury.process.cash;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.cash.CashRequest;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.cash.CashRequestRepository;
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
public class CashRequestProcess {
	
	@Autowired
	private CashRequestRepository cashRequestRepository;
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
	public <T extends Object> T approvalCash(String idx) throws Exception {
		try {
			CashRequest cash = cashRequestRepository.findByIdx(idx);
			CashRequest apCash = ObjectUtil.toObj(cash, new CashRequest());
			
			CustomUserDetails customUser = (CustomUserDetails) userService.loadUserByUsername(cash.getUserName());
			User user = customUser.getUser();
			
			apCash.setApproval(Boolean.TRUE);
			apCash.setPaymentDate(DateUtil.now());
			
			// 마일리지 요청 승인
			cashRequestRepository.save(apCash);
			
			// 이메일 전송
			mailService.approvalWithdraw(user.getEmail());
			
			return (T) Boolean.TRUE; 
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	/**
	 * 마일리지 사용자 명으로 조회
	 * 
	 * @param <T>
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seCashByUserName(String userName) throws Exception {
		try {
			return (T) cashRequestRepository.findByUserName(userName);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	/**
	 * 마일리지 목록 전체 조회
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seCashs() throws Exception {
		try {
			return (T) cashRequestRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	/**
	 * 마일리지 일렬번호로 조회
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T seCash(String idx) throws Exception {
		try {
			return (T) cashRequestRepository.findByIdx(idx);
		} catch (Exception e) {
			return (T) e;
		}
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
	public <T extends Object> T requestCash(CashRequest cr) throws Exception {
		try {
			CustomUserDetails customUser = (CustomUserDetails) userService.loadUserByUsername(cr.getUserName());
			User user = customUser.getUser();
			
			cr.setIdx(UUIDUtil.randomString());
			cr.setWithDrawDate(DateUtil.now());
			cr.setApproval(Boolean.FALSE);
			cr.setPrevCash(user.getMileage());
			
			user.setMileage(user.getMileage() - cr.getWithDrawCash());
			
			// 사용자 정보 중 마일리지 정보 수정
			userService.upUser(user, null);
			// 요청 정보 생성
			cashRequestRepository.save(cr);
			// 이메일 전송
			mailService.requestWithdraw(user.getEmail());
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
