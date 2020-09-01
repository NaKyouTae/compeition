package com.mercury.service.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.cash.CashRequest;
import com.mercury.process.cash.CashRequestProcess;

/**
 * 
 * 마일리지 요청 관련 Service
 * 
 * @author nkt
 *
 *
 * Create by User Date : 2020. 9. 1.
 *
 */
@Service
@SuppressWarnings("unchecked")
public class CashRequestService {
	
	@Autowired
	private CashRequestProcess cashRequestProcess;
	
	/**
	 * 
	 * 마일리지 출금 요청 승인
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T approvalCash(String idx) throws Exception {
		try {
			return (T) cashRequestProcess.approvalCash(idx);
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
			return cashRequestProcess.seCashByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
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
			return cashRequestProcess.seCashs();
		} catch (Exception e) {
			e.printStackTrace();
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
			return cashRequestProcess.seCash(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	/**
	 * 
	 * 마일리지 출금 요청
	 * 
	 * @param <T>
	 * @param cash
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> T requestCash(CashRequest cash) throws Exception {
		try {
			return cashRequestProcess.requestCash(cash);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
