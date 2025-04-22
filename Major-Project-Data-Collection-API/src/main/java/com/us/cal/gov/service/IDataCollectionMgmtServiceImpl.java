package com.us.cal.gov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.us.cal.gov.bindings.ChildrenInputs;
import com.us.cal.gov.bindings.CitizenAppRegisterInputs;
import com.us.cal.gov.bindings.DCSummaryOutputs;
import com.us.cal.gov.bindings.EducationInputs;
import com.us.cal.gov.bindings.IncomeInputs;
import com.us.cal.gov.bindings.PlanSelections;
import com.us.cal.gov.entity.CitizenAppRegistrationEntity;
import com.us.cal.gov.entity.DCCasesEntity;
import com.us.cal.gov.entity.DCChildrenEntity;
import com.us.cal.gov.entity.DCEducationEntity;
import com.us.cal.gov.entity.DCIncomeEntity;
import com.us.cal.gov.entity.PlansEntity;
import com.us.cal.gov.repo.ICitizenAppRegisterRepo;
import com.us.cal.gov.repo.IDCCasesEntityRepo;
import com.us.cal.gov.repo.IDCChildrenEntityRepo;
import com.us.cal.gov.repo.IDCEducationEntityRepo;
import com.us.cal.gov.repo.IDCIncomeEntityRepo;
import com.us.cal.gov.repo.IPlansEntityRepo;

@Service
public class IDataCollectionMgmtServiceImpl implements IDataCollectionMgmtService {

	@Autowired
	private IDCCasesEntityRepo caseRepo;

	@Autowired
	private ICitizenAppRegisterRepo citizenRepo;

	@Autowired
	private IPlansEntityRepo plansRepo;

	@Autowired
	private IDCIncomeEntityRepo incomeRepo;
	
	@Autowired
	private IDCEducationEntityRepo educationRepo;
	
	@Autowired
	private IDCChildrenEntityRepo childrenRepo;

	@Override
	public Long generateCaseId(Long appId) {
		Optional<CitizenAppRegistrationEntity> citizenEntity = citizenRepo.findById(appId);
		if (citizenEntity.isPresent()) {
			DCCasesEntity caseEntity = new DCCasesEntity();
			caseEntity.setAppId(appId);
			return caseRepo.save(caseEntity).getCaseNumber();
		}
		return 0l;
	}

	@Override
	public List<String> listAllPlans() {
		List<PlansEntity> plansEntity = plansRepo.findAll();
		
		List<String> listString = plansEntity.stream().map(plan->plan.getPlanName()).toList();
		return listString;
	}

	@Override
	public Long savePlanSelection(PlanSelections planSelections) {
		Optional<DCCasesEntity> caseEntity = caseRepo.findById(planSelections.getCaseNumber());
		if (caseEntity.isPresent()) {
			DCCasesEntity entity = caseEntity.get();
			entity.setPlanId(planSelections.getPlanId());
			caseRepo.save(entity);
			return planSelections.getCaseNumber();
		}
		return 0l;
	}

	@Override
	public Long saveIncomeDetails(IncomeInputs incomeInputs) {
		DCIncomeEntity incomeEntity = new DCIncomeEntity();
		BeanUtils.copyProperties(incomeInputs, incomeEntity);
		Long status = incomeRepo.save(incomeEntity).getCaseNumber();
		return status;
	}

	@Override
	public Long saveEducationDeatails(EducationInputs educationInputs) {
		DCEducationEntity  educationEntity = new DCEducationEntity();
		BeanUtils.copyProperties(educationInputs, educationEntity);
		Long status = educationRepo.save(educationEntity).getCaseNumber();
		return status;
	}

	@Override
	public DCSummaryOutputs generateSummaryReport(Long caseNumber) {
		
		Optional<DCCasesEntity> optCaseEntity = caseRepo.findById(caseNumber);
		
		System.out.println(optCaseEntity.toString());
		
		DCIncomeEntity incomeEntity = incomeRepo.findByCaseNumber(caseNumber);
		
		System.out.println(incomeEntity.toString());
		
		DCEducationEntity educationEntity = educationRepo.findByCaseNumber(caseNumber);
		
		System.out.println(educationEntity.toString());
		
		List<DCChildrenEntity> listchildrenEntities = childrenRepo.findByCaseNumber(caseNumber);
		
		System.out.println(listchildrenEntities.toString());
		
		DCSummaryOutputs summary = new DCSummaryOutputs();
		
		String planName  = null;
		Long appId = null;
		DCCasesEntity caseEntity = null;
		if(optCaseEntity.isPresent()) {
			caseEntity = optCaseEntity.get();
			appId = caseEntity.getAppId();
			Optional<PlansEntity> planEntity = plansRepo.findById(caseEntity.getPlanId());
			if(planEntity.isPresent()) {
				planName = planEntity.get().getPlanName();
			}
		}
		
		IncomeInputs incomeInputs = new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, incomeInputs);

		EducationInputs educationInputs = new EducationInputs();
		BeanUtils.copyProperties(educationEntity, educationInputs);
		
		
		
		
		List<ChildrenInputs> listChildInputs  = new ArrayList<>();
		
		
		listchildrenEntities.forEach(child->{
			ChildrenInputs inputs = new ChildrenInputs();
			BeanUtils.copyProperties(child,inputs);
			listChildInputs.add(inputs);
		});
		
		CitizenAppRegistrationEntity citizenRegEntity = new CitizenAppRegistrationEntity();
		Optional<CitizenAppRegistrationEntity> citizenEntity = citizenRepo.findById(appId);
		if(citizenEntity.isPresent()) {
			citizenRegEntity = citizenEntity.get();
		}
		
		CitizenAppRegisterInputs citiInputs = new CitizenAppRegisterInputs();
		BeanUtils.copyProperties(citizenRegEntity, citiInputs);
		
		summary.setChildrenInputs(listChildInputs);
		summary.setCitizenAppRegInputs(citiInputs);
		summary.setPlanName(planName);
		summary.setIncomeInputs(incomeInputs);
		summary.setEducationInputs(educationInputs);
		
		return summary;
	}

	@Override
	public Long saveChildrenDetails(List<ChildrenInputs> childInputs) {
		
		childInputs.forEach(child->{
			DCChildrenEntity childEntity = new DCChildrenEntity();
			BeanUtils.copyProperties(child, childEntity);
		    childrenRepo.save(childEntity);
		});
		return childInputs.get(0).getCaseNumber();
	}


}
