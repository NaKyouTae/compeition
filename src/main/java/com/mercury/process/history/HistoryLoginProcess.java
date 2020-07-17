package com.mercury.process.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.history.HistoryLogin;
import com.mercury.jpa.repository.history.HistoryLoginRepository;

@Component
@SuppressWarnings("unchecked")
public class HistoryLoginProcess {
	@Autowired
	private HistoryLoginRepository loginHistoryRepository;
	
	public <T extends Object> T seLoginHistoryByUserName(String userName) throws Exception {
		try {
			return (T) loginHistoryRepository.findByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoginHistorys() throws Exception{
		try {
			return (T) loginHistoryRepository.findAll(Sort.by(Sort.Direction.DESC, "accessDate"));
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoginHistory(String idx) throws Exception {
		try {
			return (T) loginHistoryRepository.findByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inLoginHistory(HistoryLogin history) throws Exception {
		try {
			return (T) loginHistoryRepository.save(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upLoginHistory(HistoryLogin history) throws Exception {
		try {
			return (T) loginHistoryRepository.save(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
}
