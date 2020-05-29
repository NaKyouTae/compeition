package com.competition.process.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.cash.Cash;
import com.competition.jpa.repository.cash.CashRepository;

@Component
@SuppressWarnings("unchecked")
public class CashProcess {
	
	@Autowired
	private CashRepository cashRepository;
	
	public <T extends Object> T seCashByUserName(String userName) throws Exception {
		try {
			return (T) cashRepository.findByUserName(userName);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seCashs() throws Exception {
		try {
			return (T) cashRepository.findAll();
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T seCash(String idx) throws Exception {
		try {
			return (T) cashRepository.findByIdx(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T inCash(Cash cash) throws Exception {
		try {
			return (T) cashRepository.save(cash);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T upCash(Cash cash) throws Exception {
		try {
			return (T) cashRepository.save(cash);
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	public <T extends Object> T deCash(Cash cash) throws Exception {
		try {
			cashRepository.delete(cash);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			return (T) e;
		}
	}
}
