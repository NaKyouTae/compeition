package com.competition.controller.mail;

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
import com.competition.jpa.model.mail.MailTemplate;
import com.competition.service.mail.MailTemplateService;

@RestController
@RequestMapping("/service/mails/templates")
@SuppressWarnings("unchecked")
public class MailTemplateController {
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@GetMapping("/{batch}")
	public <T extends Object> T seMailTemplateByBatchId(String batch) throws Exception {
		ControllerResponse<MailTemplate> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Template By Batch :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.seMailTemplateByBatchId(batch));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/{use}")
	public <T extends Object> T seMailTemplateByUsed(String use) throws Exception {
		ControllerResponse<List<MailTemplate>> res = new ControllerResponse<>();
		
		try {
			res.setMessage("Success Search Template By Used :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.seMailTemplateByUsed(use));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{type}")
	public <T extends Object> T seMailTemplateByType(String type) throws Exception {
		ControllerResponse<List<MailTemplate>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Template By Type :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.seMailTemplateByType(type));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("")
	public <T extends Object> T seMailTemplates() throws Exception {
		ControllerResponse<List<MailTemplate>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Template List :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.seMailTemplates());
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seMailTemplate(String idx) throws Exception {
		ControllerResponse<MailTemplate> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Template :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.seMailTemplate(idx));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inMailTemplate(@RequestBody MailTemplate temp) throws Exception {
		ControllerResponse<MailTemplate> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Insert Template :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.inMailTemplate(temp));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PutMapping("/{idx}")
	public <T extends Object> T upMailTemplate(@RequestBody MailTemplate temp) throws Exception {
		ControllerResponse<MailTemplate> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Update Template :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.upMailTemplate(temp));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@DeleteMapping("/{idx}")
	public <T extends Object> T deMailTemplate(@RequestBody MailTemplate temp) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Delete Template :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(mailTemplateService.deMailTemplate(temp));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
}
