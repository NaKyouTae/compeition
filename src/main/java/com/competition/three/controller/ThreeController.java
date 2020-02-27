package com.competition.three.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;

@RestController
@RequestMapping("/server/three")
public class ThreeController {

	@GetMapping("/list")
	public ControllerResponse<List<Object>> getList(@ModelAttribute("word") String word) throws Exception {
		ControllerResponse<List<Object>> res = new ControllerResponse<List<Object>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Three Word Lists :) "); 
			res.setResult(new ArrayList<Object>());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
}
