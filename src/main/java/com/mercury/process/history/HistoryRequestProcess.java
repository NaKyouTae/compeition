package com.mercury.process.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.model.history.HistoryRequest;
import com.mercury.jpa.repository.history.HistoryRequestRepository;

@Component
@SuppressWarnings("unchecked")
public class HistoryRequestProcess {
	
	@Autowired
	private HistoryRequestRepository historyRequestRepository;
	
	public <T extends Object> T seHistoryRequests() throws Exception{
		try {
			return (T) historyRequestRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByIdx(String idx) throws Exception{
		try {
			return (T) historyRequestRepository.findByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByUrl(String path) throws Exception{
		try {
			return (T) historyRequestRepository.findByRequestUtl(path);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByBrowser(String browser) throws Exception{
		try {
			return (T) historyRequestRepository.findByBrowser(browser);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByIp(String ip) throws Exception{
		try {
			return (T) historyRequestRepository.findByIp(ip);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByMethod(String method) throws Exception{
		try {
			return (T) historyRequestRepository.findByMethod(method);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByUserName(String username) throws Exception{
		try {
			return (T) historyRequestRepository.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T inHistoryRequest(HistoryRequest hq) throws Exception{
		try {
			return (T) historyRequestRepository.save(hq);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T upHistoryRequest(HistoryRequest hq) throws Exception{
		try {
			return (T) historyRequestRepository.save(hq);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T deHistoryRequest(HistoryRequest hq) throws Exception{
		try {
			historyRequestRepository.delete(hq);
			return (T) Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
}
