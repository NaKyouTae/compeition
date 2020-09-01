package com.mercury.controller.mileage;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.mercury.jpa.model.mileage.Mileage;
import com.mercury.service.mileage.MileageService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/mileages")
public class MileageController {
	
	Logger log = LogManager.getLogger(MileageController.class);
	
	@Autowired
	private MileageService mileageService;
		
	@GetMapping("/users")
	public <T extends Object> T seMileageByUserName(String username) throws Exception{
		ControllerResponse<List<Mileage>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Mileage List By User Name :) ");
			res.setResult(mileageService.seMileageByUserName(username));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}	
	
	@GetMapping("")
	public <T extends Object> T seMileages() throws Exception{
		ControllerResponse<List<Mileage>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Mileage List :) ");
			res.setResult(mileageService.seMileages());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seMileage(String idx) throws Exception{
		ControllerResponse<Mileage> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Mileage :) ");
			res.setResult(mileageService.seMileage(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PostMapping
	public <T extends Object> T inMileage(@RequestBody Mileage mileage) throws Exception{
		ControllerResponse<Mileage> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Mileage :) ");
			res.setResult(mileageService.inMileage(mileage));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PutMapping
	public <T extends Object> T upMileage(@RequestBody Mileage mileage) throws Exception{
		ControllerResponse<Mileage> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Mileage :) ");
			res.setResult(mileageService.upMileage(mileage));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@DeleteMapping
	public <T extends Object> T deMileage(@RequestBody Mileage mileage) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Mileage :) ");
			res.setResult(mileageService.deMileage(mileage));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
}
