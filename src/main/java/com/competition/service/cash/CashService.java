package com.competition.service.cash;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.cash.Cash;
import com.competition.process.cash.CashProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class CashService {
	
	@Autowired
	private CashProcess cashProcess;
	
	public <T extends Object> T seCashByUserName(String userName) throws Exception {
		try {
			return cashProcess.seCashByUserName(userName);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seCash(String idx) throws Exception {
		try {
			return cashProcess.seCash(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T inCash(Cash cash) throws Exception {
		try {
			cash.setIdx(UUID.randomUUID().toString().replace("-", ""));
			cash.setWithDrawDate(DateUtil.now());
			return cashProcess.inCash(cash);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T upCash(Cash cash) throws Exception {
		try {
			return cashProcess.upCash(cash);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T deCash(Cash cash) throws Exception {
		try {
			return cashProcess.deCash(cash);
		} catch (Exception e) {
			return (T) e;
		}
	}
}
