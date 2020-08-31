package com.mercury.process.cash;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.cash.CashRequest;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.repository.cash.CashRequestRepository;
import com.mercury.service.user.UserService;
import com.mercury.user.CustomUserDetails;
import com.mercury.util.DateUtil;
import com.mercury.util.ObjectUtil;
import com.mercury.util.UUIDUtil;

@Component
@SuppressWarnings("unchecked")
@Transactional
public class CashRequestProcess {
	
	@Autowired
	private CashRequestRepository cashRequestRepository;
	@Autowired
	private UserService userService;
	
	public <T extends Object> T approvalCash(String idx) throws Exception {
		try {
			
			CashRequest cash = cashRequestRepository.findByIdx(idx);
			CashRequest apCash = ObjectUtil.toObj(cash, new CashRequest());
			
			apCash.setApproval(Boolean.TRUE);
			apCash.setPaymentDate(DateUtil.now());
			
			// 마일리지 요청 승인
			cashRequestRepository.save(apCash);
			
			return (T) Boolean.TRUE; 
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seCashByUserName(String userName) throws Exception {
		try {
			return (T) cashRequestRepository.findByUserName(userName);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seCashs() throws Exception {
		try {
			return (T) cashRequestRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seCash(String idx) throws Exception {
		try {
			return (T) cashRequestRepository.findByIdx(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T inCash(CashRequest cr) throws Exception {
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
			
			return (T) cashRequestRepository.save(cr);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
