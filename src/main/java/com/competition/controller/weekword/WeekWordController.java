package com.competition.controller.weekword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.service.weekword.WeekWordService;

@RestController
@RequestMapping("/service/weekword")
public class WeekWordController {

	@Autowired
	private WeekWordService weekWordService;
	
	@GetMapping("/lists")
	public ControllerResponse<List<Object>> geWeekWords() throws Exception {
		ControllerResponse<List<Object>> res = new ControllerResponse<List<Object>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Week Word Lists :) "); 
			res.setResult(weekWordService.getWeekWords());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
}
