package com.mercury.process.honor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercury.jpa.repository.honor.HonorRepository;

@Component
@SuppressWarnings("unchecked")
public class HonorProcess {
	@Autowired
	private HonorRepository honorRepository;
	
	public <T extends Object> T seHonors() throws Exception{
		try {
			return (T) honorRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByIdx(String idx) throws Exception{
		try {
			return (T) honorRepository.findByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByUserName(String username) throws Exception{
		try {
			return (T) honorRepository.findByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByContentIdx(String contentIdx) throws Exception{
		try {
			return (T) honorRepository.findByContentIdx(contentIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seHonorByWord(String word) throws Exception{
		try {
			return (T) honorRepository.findByWord(word);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
