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
import com.competition.jpa.model.three.WordThree;
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
	@PostMapping("/threes")
	public ControllerResponse<WordThree> inThree(@RequestBody WordThree word) throws Exception {
		ControllerResponse<WordThree> res = new ControllerResponse<WordThree>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Three Word :) "); 
			res.setResult(threeService.inThree(word));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	@PutMapping("/threes/{idx}")
	public ControllerResponse<WordThree> upThree(@RequestBody WordThree word) throws Exception {
		ControllerResponse<WordThree> res = new ControllerResponse<WordThree>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Three Word :) "); 
			res.setResult(threeService.upThree(word));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	@DeleteMapping("/threes/{idx}")
	public ControllerResponse<WordThree> deThree(@RequestBody WordThree word) throws Exception {
		ControllerResponse<WordThree> res = new ControllerResponse<WordThree>();
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
