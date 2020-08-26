package com.mercury.service.cash;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.cash.Cash;
import com.mercury.process.cash.CashProcess;
import com.mercury.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class CashService {
	
	@Autowired
	private CashProcess cashProcess;
	
	public <T extends Object> T seCashByUserName(String userName) throws Exception {
		try {
			return cashProcess.seCashByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seCashs() throws Exception {
		try {
			return cashProcess.seCashs();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seCash(String idx) throws Exception {
		try {
			return cashProcess.seCash(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inCash(Cash cash) throws Exception {
		try {
			cash.setIdx(UUID.randomUUID().toString().replace("-", ""));
			cash.setPaymentDate(DateUtil.now());
			return cashProcess.inCash(cash);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upCash(Cash cash) throws Exception {
		try {
			return cashProcess.upCash(cash);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deCash(Cash cash) throws Exception {
		try {
			return cashProcess.deCash(cash);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T deCashAllEntities(List<Cash> cashs) throws Exception {
		try {
			return cashProcess.deCashAllEntities(cashs);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
