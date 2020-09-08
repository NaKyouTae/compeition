package com.mercury.process.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.history.HistoryLogin;
import com.mercury.jpa.repository.history.HistoryLoginRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class HistoryLoginProcess {
	@Autowired
	private HistoryLoginRepository loginHistoryRepository;

	public <T extends Object> T seLoginHistoryByUserName(String userName)
			throws Exception {
		return (T) loginHistoryRepository.findByUserName(userName);
	}

	public <T extends Object> T seLoginHistorys() throws Exception {
		return (T) loginHistoryRepository
				.findAll(Sort.by(Sort.Direction.DESC, "accessDate"));
	}

	public <T extends Object> T seLoginHistory(String idx) throws Exception {
		return (T) loginHistoryRepository.findByIdx(idx);
	}

	public <T extends Object> T inLoginHistory(HistoryLogin history)
			throws Exception {
		return (T) loginHistoryRepository.save(history);
	}

	public <T extends Object> T upLoginHistory(HistoryLogin history)
			throws Exception {
		return (T) loginHistoryRepository.save(history);
	}

}
