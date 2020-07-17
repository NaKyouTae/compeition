package com.mercury.controller.two;

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

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.two.Two;
import com.mercury.service.two.TwoService;

@RestController
@RequestMapping("/service/twice")
public class TwoController {

	@Autowired
	private TwoService twoService;
	
	@GetMapping("/popular")
	public ControllerResponse<List<Two>> getPopular(){
		ControllerResponse<List<Two>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Two Popular Lists :) "); 
			res.setResult(twoService.getPopular());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@GetMapping("")
	public ControllerResponse<List<Two>> getList() throws Exception {
		ControllerResponse<List<Two>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Two Lists :) "); 
			res.setResult(twoService.getList());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@GetMapping("/user")
	public ControllerResponse<List<Two>> seByUser(String userIdx) throws Exception {
		ControllerResponse<List<Two>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search By User List :) "); 
			res.setResult(twoService.seByUser(userIdx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@GetMapping("/words")
	public ControllerResponse<List<Two>> seByWord() throws Exception {
		ControllerResponse<List<Two>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Two By Word List :) "); 
			res.setResult(twoService.seByWord());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@PostMapping("")
	public ControllerResponse<Two> inTwo(@RequestBody Two two) throws Exception {
		ControllerResponse<Two> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Two :) "); 
			res.setResult(twoService.inTwo(two));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	@PutMapping("/{idx}")
	public ControllerResponse<Two> upTwo(@RequestBody Two two) throws Exception {
		ControllerResponse<Two> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Two :) "); 
			res.setResult(twoService.upTwo(two));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	@DeleteMapping("/{idx}")
	public ControllerResponse<Two> deTwo(@RequestBody Two two) throws Exception {
		ControllerResponse<Two> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Two :) "); 
			res.setResult(twoService.deTwo(two));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
}
