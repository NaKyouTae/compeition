package com.mercury.process.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.history.HistoryRequest;
import com.mercury.jpa.repository.history.HistoryRequestRepository;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class HistoryRequestProcess {

	@Autowired
	private HistoryRequestRepository historyRequestRepository;

	public <T extends Object> T seHistoryRequests() throws Exception {
		return (T) historyRequestRepository.findAll();
	}
	public <T extends Object> T seHistoryRequestByIdx(String idx)
			throws Exception {
		return (T) historyRequestRepository.findByIdx(idx);
	}
	public <T extends Object> T seHistoryRequestByUrl(String path)
			throws Exception {
		return (T) historyRequestRepository.findByRequestUrl(path);
	}
	public <T extends Object> T seHistoryRequestByBrowser(String browser)
			throws Exception {
		return (T) historyRequestRepository.findByBrowser(browser);
	}
	public <T extends Object> T seHistoryRequestByIp(String ip)
			throws Exception {
		return (T) historyRequestRepository.findByIp(ip);
	}
	public <T extends Object> T seHistoryRequestByMethod(String method)
			throws Exception {
		return (T) historyRequestRepository.findByMethod(method);
	}
	public <T extends Object> T seHistoryRequestByUserName(String username)
			throws Exception {
		return (T) historyRequestRepository.findByUsername(username);
	}
	public <T extends Object> T inHistoryRequest(HistoryRequest hq)
			throws Exception {
		return (T) historyRequestRepository.save(hq);
	}
	public <T extends Object> T upHistoryRequest(HistoryRequest hq)
			throws Exception {
		return (T) historyRequestRepository.save(hq);
	}
	public <T extends Object> T deHistoryRequest(HistoryRequest hq)
			throws Exception {
		historyRequestRepository.delete(hq);
		return (T) Boolean.TRUE;
	}

}
