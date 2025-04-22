package com.vj.ms;

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

import com.vj.entity.CaseWorkerEntity;
import com.vj.service.ICaseWorkerMgmtService;

@RestController
@RequestMapping("/case-worker")
public class CaseWorkerRestController {

	@Autowired
	private ICaseWorkerMgmtService service;

	@PostMapping("/registerPlan")
	public ResponseEntity<String> saveTravelPlan(@RequestBody CaseWorkerEntity plan) {
		String res = service.registerCaseWorker(plan);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@GetMapping("/display-plan")
	public ResponseEntity<?> displayAllTravelPlans() {

		List<CaseWorkerEntity> list = service.showAllCaseWorkers();
		return new ResponseEntity<List<CaseWorkerEntity>>(list, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> displayPlanById(@PathVariable Long id) {

		CaseWorkerEntity list = service.showWorkerById(id);
		return new ResponseEntity<CaseWorkerEntity>(list, HttpStatus.OK);

	}

	@PostMapping("/update-travel")
	public ResponseEntity<String> updatePlan(@RequestBody CaseWorkerEntity plan) {

		String list = service.updateWorker(plan);
		return new ResponseEntity<String>(list, HttpStatus.OK);

	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<String> deletePlan(@PathVariable Long id) {

		String list = service.deleteWorker(id);
		return new ResponseEntity<String>(list, HttpStatus.OK);

	}

	@PostMapping("/change-status/{id}/{status}")
	public ResponseEntity<String> changeActiveStatus(@PathVariable Long id, @PathVariable String status) {

		String list = service.changeWorkerStatus(id, status);
		return new ResponseEntity<String>(list, HttpStatus.OK);

	}
}
