package com.competition.service.three;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.process.three.ThreeProcess;

@Service
public class ThreeService {
	
	@Autowired
	private ThreeProcess threeProcess;
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getList() {
		return (T) threeProcess.getList();
	}
}
