package com.competition.controller.three;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.three.Three;
import com.competition.service.three.ThreeService;

@RestController
@RequestMapping("/service/three")
public class ThreeController {

	@Autowired
	private ThreeService threeService;
	
	@GetMapping("/popular")
	public ControllerResponse<List<Three>> getPopular(){
		ControllerResponse<List<Three>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Three Popular Lists :) "); 
			res.setResult(threeService.getPopular());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@GetMapping("")
	public ControllerResponse<List<Three>> getList() throws Exception {
		ControllerResponse<List<Three>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Three Lists :) "); 
			res.setResult(threeService.getList());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@PostMapping("")
	public ControllerResponse<Three> inThree(@RequestBody Three word) throws Exception {
		ControllerResponse<Three> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Three :) "); 
			res.setResult(threeService.inThree(word));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	@PutMapping("/{idx}")
	public ControllerResponse<Three> upThree(@RequestBody Three word) throws Exception {
		ControllerResponse<Three> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Three :) "); 
			res.setResult(threeService.upThree(word));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	@DeleteMapping("/{idx}")
	public ControllerResponse<Boolean> deThree(@RequestBody Three word) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Three Word :) "); 
			res.setResult(threeService.deThree(word));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
}
