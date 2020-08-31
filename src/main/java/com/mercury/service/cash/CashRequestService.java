package com.mercury.service.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.cash.CashRequest;
import com.mercury.process.cash.CashRequestProcess;

@Service
@SuppressWarnings("unchecked")
public class CashRequestService {
	
	@Autowired
	private CashRequestProcess cashRequestProcess;
	
	public <T extends Object> T approvalCash(String idx) throws Exception {
		try {
			return (T) cashRequestProcess.approvalCash(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seCashByUserName(String userName) throws Exception {
		try {
			return cashRequestProcess.seCashByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seCashs() throws Exception {
		try {
			return cashRequestProcess.seCashs();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seCash(String idx) throws Exception {
		try {
			return cashRequestProcess.seCash(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inCash(CashRequest cash) throws Exception {
		try {
			return cashRequestProcess.inCash(cash);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
