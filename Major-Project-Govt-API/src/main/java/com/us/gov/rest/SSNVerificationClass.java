package com.us.gov.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssn-api")
public class SSNVerificationClass {

	@GetMapping("/ssn-verify/{ssnid}")
	public ResponseEntity<String> SSNverifyMethod(@PathVariable Integer ssnid) {
		Integer stateCode = ssnid % 100;
		if (String.valueOf(ssnid).length() != 9) {
			return new ResponseEntity<String>("Invalid", HttpStatus.BAD_REQUEST);
		} else if (stateCode == 01) {
			return new ResponseEntity<String>("California", HttpStatus.ACCEPTED);  
		} else if (stateCode == 02) {
			return new ResponseEntity<String>("Indiana", HttpStatus.ACCEPTED);
		} else if (stateCode == 03) {
			return new ResponseEntity<String>("Hawaii", HttpStatus.ACCEPTED);
		} else if (stateCode == 04) {
			return new ResponseEntity<String>("Alaska", HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>("Invaild SSN", HttpStatus.BAD_REQUEST);
		}
	}
}
