package com.mercury.process.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.cash.CashRequest;
import com.mercury.jpa.repository.cash.CashRequestRepository;
import com.mercury.util.DateUtil;
import com.mercury.util.ObjectUtil;
import com.mercury.util.UUIDUtil;

@Component
@SuppressWarnings("unchecked")
public class CashRequestProcess {
	
	@Autowired
	private CashRequestRepository cashRequestRepository;
	
	
	
	public <T extends Object> T approvalCash(String idx) throws Exception {
		try {
			
			CashRequest cash = cashRequestRepository.findByIdx(idx);
			CashRequest apCash = ObjectUtil.toObj(cash, new CashRequest());
			
			apCash.setApproval(Boolean.TRUE);
			
			return (T) cashRequestRepository.save(cash);
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
			cr.setIdx(UUIDUtil.randomString());
			cr.setWithDrawDate(DateUtil.now());
			return (T) cashRequestRepository.save(cr);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
