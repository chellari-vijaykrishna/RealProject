package com.vj.service;

import java.util.List;
import java.util.Map;

import com.vj.entity.PlansEntity;

public interface IPlanMgmtService {
	
	public String registerPlan(PlansEntity plan);
	
	public Map<Integer, String> getPlanCata();
	
	public List<PlansEntity> showPlan();
	
	public PlansEntity showPlanById(Long id);
	
	public String updatePlan(PlansEntity plan);
	
	public String deletePlan(Long id);
	
	public String changePlanStatus(Long planid, String status);

}
