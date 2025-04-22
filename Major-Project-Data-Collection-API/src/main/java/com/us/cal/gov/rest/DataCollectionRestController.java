package com.us.cal.gov.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.us.cal.gov.bindings.ChildrenInputs;
import com.us.cal.gov.bindings.DCSummaryOutputs;
import com.us.cal.gov.bindings.EducationInputs;
import com.us.cal.gov.bindings.IncomeInputs;
import com.us.cal.gov.bindings.PlanSelections;
import com.us.cal.gov.service.IDataCollectionMgmtService;

@RestController
@RequestMapping("/dc-api")
public class DataCollectionRestController {
	
	@Autowired
	private IDataCollectionMgmtService service;
	
	@PostMapping("/generate/{appId}")
	public ResponseEntity<String> generateCaseId(@PathVariable Long appId){
		Long status = service.generateCaseId(appId);
		   return new ResponseEntity<String>("Generated "+status,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/add-plan")
	public ResponseEntity<String> addPlan(@RequestBody PlanSelections planSelections){
		Long status = service.savePlanSelection(planSelections);
		   return new ResponseEntity<String>("Added ::"+status,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get-listsOfPlans")
	public ResponseEntity<List<String>> fetchAllListOfPlans(){
		List<String> listPlans = service.listAllPlans();
		return new ResponseEntity<List<String>>(listPlans,HttpStatus.OK);
	}
	
	@PostMapping("/add-incomeDetails")
	public ResponseEntity<String> saveIncomeDetails(@RequestBody IncomeInputs incomeInputs){
	Long status = service.saveIncomeDetails(incomeInputs);
		return new ResponseEntity<String>(status+"",HttpStatus.OK);
	}
	@PostMapping("/add-educationDetails")
	public ResponseEntity<String> saveEducaionDetails(@RequestBody EducationInputs educationInputs){
	Long status = service.saveEducationDeatails(educationInputs);
		return new ResponseEntity<String>(status+"",HttpStatus.OK);
	}
	
	@PostMapping("/add-saveChildrenDetails")
	public ResponseEntity<String> saveChildrenDetails(@RequestBody List<ChildrenInputs> childrenInputs){
	Long status = service.saveChildrenDetails(childrenInputs);
		return new ResponseEntity<String>(status+"",HttpStatus.OK);
	}
	
	@GetMapping("/get-summary/{caseNumber}")
	public ResponseEntity<DCSummaryOutputs> getSummary(@PathVariable Long caseNumber){
	DCSummaryOutputs status = service.generateSummaryReport(caseNumber);
		return new ResponseEntity<DCSummaryOutputs>(status,HttpStatus.OK);
	}
	
}
