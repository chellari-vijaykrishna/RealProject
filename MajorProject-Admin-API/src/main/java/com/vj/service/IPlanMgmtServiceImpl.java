package com.vj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vj.commons.PlanConstants;
import com.vj.config.AppConfig;
import com.vj.entity.PlanCatagoryEntity;
import com.vj.entity.PlansEntity;
import com.vj.repo.IPlanCatagoryRepo;
import com.vj.repo.IPlanRepo;



@Service("IPlanMgmtImpl")
public class IPlanMgmtServiceImpl implements IPlanMgmtService {
	
	@Autowired
	private IPlanRepo planRepo;
	@Autowired
	private IPlanCatagoryRepo planCataRepo;

	private Map<String, String> messages;
	
	public IPlanMgmtServiceImpl (AppConfig props){
		messages=props.getMessages();
	}

	@Override
	public String registerPlan(PlansEntity plan) {	
		PlansEntity savedPlan=	planRepo.save(plan);
		return savedPlan!=null?messages.get(PlanConstants.SAVE_SUCESS)+savedPlan.getPlanId():messages
				.get(PlanConstants.SAVE_FAIL);
	}

	@Override
	public Map<Integer, String> getPlanCata() {
		List<PlanCatagoryEntity> planCata = planCataRepo.findAll();
		Map<Integer, String> map = new HashMap<Integer, String>();
		planCata.forEach(list->{
			map.put(list.getCatagoryId(),list.getCatagoryName());
		});
		return map;
	}

	@Override
	public List<PlansEntity> showPlan() {
		List<PlansEntity> plan = planRepo.findAll();
		System.out.println("");
		return plan;
	}

	@Override
	public PlansEntity showPlanById(Long id) {
		return planRepo.findById(id)
				.orElseThrow(
						()->new IllegalArgumentException(messages.get(PlanConstants.FIND_FAIL))
						);
	}

	@Override
	public String updatePlan(PlansEntity plan) {
		
		Optional<PlansEntity> opt = planRepo.findById(plan.getPlanId());
		if(opt.isPresent()) {
			planRepo.save(plan);
		return messages.get(PlanConstants.UPDATE_SUCESS);
		}
		else {
			return messages.get(PlanConstants.UPDATE_FAIL);
		}
	}

	@Override
	public String deletePlan(Long id) {
		Optional<PlansEntity> opt = planRepo.findById(id);
		if(opt.isPresent()) {
			planRepo.deleteById(id);
		return messages.get(PlanConstants.DELETE_SUCESS);
		}
		else {
			return messages.get(PlanConstants.DELETE_FAIL);
		}
	}

	@Override
	public String changePlanStatus(Long planid, String status) {
		System.out.println(planid+status);
		Optional<PlansEntity> opt = planRepo.findById(planid);
			if(opt.isPresent()) {
				System.out.println(opt.toString());
				PlansEntity plan =opt.get();
				plan.setActiveSwitch(status);
				planRepo.save(plan);
				System.out.println(opt.toString());
			return messages.get(PlanConstants.ACTIVE_CHANGED_SUCESS);
			}
			else {
				return messages.get(PlanConstants.ACTIVE_CHANGED_FAIL);
			}
	}

}
