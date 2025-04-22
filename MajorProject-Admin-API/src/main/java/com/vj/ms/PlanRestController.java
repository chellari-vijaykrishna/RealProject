package com.vj.ms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vj.entity.PlansEntity;
import com.vj.service.IPlanMgmtServiceImpl;

@RestController
@RequestMapping("admin/api")
public class PlanRestController {

	@Autowired
	private IPlanMgmtServiceImpl service;

	@GetMapping("/show-report-cata")
	public ResponseEntity<?> showTravelPlans() {
		Map<Integer, String> map = service.getPlanCata();
		return new ResponseEntity<Map<Integer, String>>(map, HttpStatus.OK);
	}

	@PostMapping("/registerPlan")
	public ResponseEntity<String> saveTravelPlan(@RequestBody PlansEntity plan) {
		System.out.println(plan.toString());
		String res = service.registerPlan(plan);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

	@GetMapping("/display-plan")
	public ResponseEntity<?> displayAllTravelPlans() {

		List<PlansEntity> list = service.showPlan();
		return new ResponseEntity<List<PlansEntity>>(list, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> displayPlanById(@PathVariable Long id) {

		PlansEntity list = service.showPlanById(id);
		return new ResponseEntity<PlansEntity>(list, HttpStatus.OK);

	}

	@PostMapping("/update-travel")
	public ResponseEntity<String> updatePlan(@RequestBody PlansEntity plan) {

		String list = service.updatePlan(plan);
		return new ResponseEntity<String>(list, HttpStatus.OK);

	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<String> deletePlan(@PathVariable Long id) {

		String list = service.deletePlan(id);
		return new ResponseEntity<String>(list, HttpStatus.OK);

	}

	@PostMapping("/change-status/{id}/{status}")
	public ResponseEntity<String> changeActiveStatus(@PathVariable Long id, @PathVariable String status) {

		String list = service.changePlanStatus(id, status);
		return new ResponseEntity<String>(list, HttpStatus.OK);

	}

}