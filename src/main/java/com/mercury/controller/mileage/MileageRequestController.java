package com.mercury.controller.mileage;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.mileage.MileageRequest;
import com.mercury.service.mileage.MileageRequestService;

/**
 * 마일리지 요청 관련 Controller
 * 
 * @author nkt
 *
 *
 * Create by User Date : 2020. 9. 1.
 *
 */
@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/mileagerequest")
public class MileageRequestController {
	
	Logger log = LogManager.getLogger(MileageRequestController.class);
	
	@Autowired
	private MileageRequestService mileageRequestService;
	
	/**
	 * 마일리지 요청 승인
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/approval/{idx}")
	public <T extends Object> T approvalMileage(@PathVariable(value = "idx") String idx) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Approval Mileage Request :) ");
			res.setResult(mileageRequestService.approvalMileage(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	/**
	 * 마일리지 사용자 명으로 조회 
	 * 
	 * @param <T>
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/users")
	public <T extends Object> T seMileageByUserName(String username) throws Exception{
		ControllerResponse<List<MileageRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Mileage Request List By User Name :) ");
			res.setResult(mileageRequestService.seMileageByUserName(username));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	/**
	 * 마일리지 요청 전체 목록 조회
	 * 
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	@GetMapping
	public <T extends Object> T seMileages() throws Exception{
		ControllerResponse<List<MileageRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Mileage Request List :) ");
			res.setResult(mileageRequestService.seMileages());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	/**
	 * 마일리지 일렬번호로 조회
	 * 
	 * @param <T>
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/{idx}")
	public <T extends Object> T seMileage(@PathParam(value = "idx") String idx) throws Exception{
		ControllerResponse<MileageRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Mileage Request :) ");
			res.setResult(mileageRequestService.seMileage(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	/**
	 * 마일리지 출금 요청
	 * 
	 * @param <T>
	 * @param mileage
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public <T extends Object> T requestMileage(@RequestBody MileageRequest mileage) throws Exception{
		ControllerResponse<MileageRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Mileage Request :) ");
			res.setResult(mileageRequestService.requestMileage(mileage));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
}
