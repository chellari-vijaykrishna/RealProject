package com.us.cal.gov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.us.cal.gov.bindings.ApplicationRegistrationsInputs;
import com.us.cal.gov.service.ICitizenAppRegistrationEntityService;

@RestController
@RequestMapping("/app-regi")
public class AppRegistrationRestController {
	
	@Autowired
	private ICitizenAppRegistrationEntityService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCitizen(@RequestBody ApplicationRegistrationsInputs inputs) throws Exception{
		
		System.out.println(inputs.toString());
		
		if(service.CitizenRegistration(inputs)>0)
		return new ResponseEntity<String>("Citizen Registred with : "+service.CitizenRegistration(inputs),HttpStatus.CREATED);
		else
		return new ResponseEntity<String>("not Registered"+service.CitizenRegistration(inputs),HttpStatus.CREATED);

	}
}
