package com.us.cal.gov.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.us.cal.gov.bindings.COTriggerSumarry;
import com.us.cal.gov.service.ICorrespondingService;

@RestController
@RequestMapping("/corresponding-api")
public class CorrespodingRestController {
	
	@Autowired
	private ICorrespondingService service;
	
	@GetMapping("/process")
	public ResponseEntity<COTriggerSumarry> processTriggers() throws Exception {
		COTriggerSumarry coTriggerSumarry  = service.processTriggers();
		return new ResponseEntity<COTriggerSumarry>(coTriggerSumarry,HttpStatus.OK);
		
	}

}
