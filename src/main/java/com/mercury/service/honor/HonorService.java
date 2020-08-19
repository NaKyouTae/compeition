package com.mercury.service.honor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.process.honor.HonorProcess;

@Service
@SuppressWarnings("unchecked")
public class HonorService {
	@Autowired
	private HonorProcess honorProcess;
	
	public <T extends Object> T seHonors() throws Exception{
		try {
			return (T) honorProcess.seHonors();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByIdx(String idx) throws Exception{
		try {
			return (T) honorProcess.seHonorByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByUserName(String username) throws Exception{
		try {
			return (T) honorProcess.seHonorByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByContentIdx(String contentIdx) throws Exception{
		try {
			return (T) honorProcess.seHonorByContentIdx(contentIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByWord(String word) throws Exception{
		try {
			return (T) honorProcess.seHonorByWord(word);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
