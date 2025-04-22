package com.us.cal.gov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.us.cal.gov.bindings.CitizenEligibiltyOutputs;
import com.us.cal.gov.service.ICitizenEligibilityDeterminationService;

@RestController
@RequestMapping("/citizen-determination-api")
public class CitizenDeterminatioRestController {
	
	@Autowired
	private ICitizenEligibilityDeterminationService service;
	
	@PostMapping("/check/{CaseNumber}")
	public ResponseEntity<CitizenEligibiltyOutputs> checkEligiblity(@PathVariable Long CaseNumber){
		System.out.println(CaseNumber);
		CitizenEligibiltyOutputs output = service.eligiblityDetermination(CaseNumber);
		return new ResponseEntity<CitizenEligibiltyOutputs>(output,HttpStatus.OK);
		
	}
}
