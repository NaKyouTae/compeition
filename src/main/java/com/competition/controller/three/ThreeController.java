package com.competition.controller.three;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.service.three.ThreeService;

@RestController
@RequestMapping("/service/three")
public class ThreeController {

	@Autowired
	private ThreeService threeService;
	
	@GetMapping("/lists")
	public ControllerResponse<List<Object>> getList() throws Exception {
		ControllerResponse<List<Object>> res = new ControllerResponse<List<Object>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Three Word Lists :) "); 
			res.setResult(threeService.getList());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
}
