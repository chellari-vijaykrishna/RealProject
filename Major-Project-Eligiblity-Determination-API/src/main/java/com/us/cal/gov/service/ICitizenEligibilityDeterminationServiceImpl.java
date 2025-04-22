package com.us.cal.gov.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.us.cal.gov.bindings.CitizenEligibiltyOutputs;
import com.us.cal.gov.entity.CitizenAppRegistrationEntity;
import com.us.cal.gov.entity.DCCasesEntity;
import com.us.cal.gov.entity.DCChildrenEntity;
import com.us.cal.gov.entity.DCEducationEntity;
import com.us.cal.gov.entity.DCIncomeEntity;
import com.us.cal.gov.entity.DCTriggerEntity;
import com.us.cal.gov.entity.EligiblityDetailsEntity;
import com.us.cal.gov.entity.PlansEntity;
import com.us.cal.gov.repo.ICitizenAppRegisterRepo;
import com.us.cal.gov.repo.IDCCasesEntityRepo;
import com.us.cal.gov.repo.IDCChildrenEntityRepo;
import com.us.cal.gov.repo.IDCEducationEntityRepo;
import com.us.cal.gov.repo.IDCIncomeEntityRepo;
import com.us.cal.gov.repo.IDCTriggerEntityRepo;
import com.us.cal.gov.repo.IEligiblityDetailsEntityRepo;
import com.us.cal.gov.repo.IPlansEntityRepo;

@Service
public class ICitizenEligibilityDeterminationServiceImpl implements ICitizenEligibilityDeterminationService {

	@Autowired
	private IDCCasesEntityRepo caseRepo;

	@Autowired
	private ICitizenAppRegisterRepo citizenRepo;

	@Autowired
	private IPlansEntityRepo plansRepo;

	@Autowired
	private IDCIncomeEntityRepo incomeRepo;

	@Autowired
	private IDCChildrenEntityRepo childRepo;

	@Autowired
	private IEligiblityDetailsEntityRepo eligibliyRepo;

	@Autowired
	private IDCEducationEntityRepo eductionRepo;

	@Autowired
	private IDCTriggerEntityRepo triggerRepo;

	@Override
	public CitizenEligibiltyOutputs eligiblityDetermination(Long caseNumber) {
		Long appId = null;
		Long planId = null;

		Optional<DCCasesEntity> caseEntity = caseRepo.findById(caseNumber);
		if (caseEntity.isPresent()) {
			DCCasesEntity caseEn = caseEntity.get();
			appId = caseEn.getAppId();
			planId = caseEn.getPlanId();
		}

		String holderName = null;
		int holderAge = 0;

		Optional<CitizenAppRegistrationEntity> citizenAppRegister = citizenRepo.findById(appId);

		if (citizenAppRegister.isPresent()) {
			CitizenAppRegistrationEntity citiEligiEntity = citizenAppRegister.get();
			holderName = citiEligiEntity.getFullName();
			System.out.println(holderName);
//			holderAge = Period.between(citiEligiEntity.getDob(), LocalDate.now().getYear());
			holderAge = Period.between(citiEligiEntity.getDob(), LocalDate.now()).getYears();

		}

		String planName = null;

		Optional<PlansEntity> plansEntity = plansRepo.findById(planId);

		if (plansEntity.isPresent()) {
			PlansEntity plan = plansEntity.get();
			planName = plan.getPlanName();
		}

		CitizenEligibiltyOutputs citizenEligibiltyOutputs = applyAllConditions(holderAge,planName, caseNumber,
				holderName);
		EligiblityDetailsEntity ciEliOutput = new EligiblityDetailsEntity();
		BeanUtils.copyProperties(citizenEligibiltyOutputs, ciEliOutput);
		eligibliyRepo.save(ciEliOutput);

		DCTriggerEntity triggerEntity = new DCTriggerEntity();
		triggerEntity.setCaseNumber(caseNumber);
		triggerEntity.setTriggerStatus("pending");
		triggerRepo.save(triggerEntity);

		return citizenEligibiltyOutputs;
	}

	private CitizenEligibiltyOutputs applyAllConditions(int holderAge, String planName, Long caseNumber,
			String holderName) {
		CitizenEligibiltyOutputs ciEliOutput = new CitizenEligibiltyOutputs();

		ciEliOutput.setPlanName(planName);
		ciEliOutput.setHolderName(holderName);

		DCIncomeEntity incomeEntity = incomeRepo.findByCaseNumber(caseNumber);
		Long citizenIncome = incomeEntity.getCitizenIncome();
		Long propertyIncome = incomeEntity.getPropertyIncome();

		if (planName.equalsIgnoreCase("SNAP")) {
			if (citizenIncome <= 300) {
				ciEliOutput.setPlanStatus("Approved");
				ciEliOutput.setBenifitAmt(300l);
			} else {
				ciEliOutput.setDenialReason("High Income");
				ciEliOutput.setPlanStatus("Deniey");
			}
		} else if (planName.equalsIgnoreCase("CCAP")) {
			Boolean kidsCondition = false;
			Boolean kidsAge = true;

			List<DCChildrenEntity> listChilds = childRepo.findByCaseNumber(caseNumber);
			if (listChilds.isEmpty()) {
				kidsCondition = true;

				for (DCChildrenEntity child : listChilds) {
					int age = Period.between(child.getDob(), LocalDate.now()).getYears();
					if (age > 16) {
						kidsAge = false;
						break;
					}
				}
			}
			if (citizenIncome <= 300 && kidsCondition && kidsAge) {
				ciEliOutput.setPlanStatus("Approved");
				ciEliOutput.setBenifitAmt(250l);
			} else {
				ciEliOutput.setDenialReason("Not Eligiltiy");
				ciEliOutput.setPlanStatus("Rejected");
			}

		} else if (planName.equalsIgnoreCase("MEDAID")) {
			if (citizenIncome <= 300 && propertyIncome == 0) {
				ciEliOutput.setPlanStatus("Approved");
				ciEliOutput.setBenifitAmt(330l);
			} else {
				ciEliOutput.setDenialReason("Not Eligiltiy");
				ciEliOutput.setPlanStatus("Rejected");
			}
		} else if (planName.equalsIgnoreCase("MEDCARE")) {
			if (holderAge >= 65) {
				ciEliOutput.setPlanStatus("Approved");
				ciEliOutput.setBenifitAmt(330l);
			} else {
				ciEliOutput.setDenialReason("Not Eligiltiy");
				ciEliOutput.setPlanStatus("Rejected");
			}
		} else if (planName.equalsIgnoreCase("CAJW")) {
			DCEducationEntity eduEntity = eductionRepo.findByCaseNumber(caseNumber);
			if (eduEntity.getPassOutYear() < LocalDate.now().getYear() && citizenIncome == 0l) {
				ciEliOutput.setPlanStatus("Approved");
				ciEliOutput.setBenifitAmt(330l);
			} else {
				ciEliOutput.setDenialReason("Not Eligiltiy");
				ciEliOutput.setPlanStatus("Rejected");
			}
		} else if (planName.equalsIgnoreCase("GHP")) {

			if (holderAge >= 1) {
				ciEliOutput.setPlanStatus("Approved");
				ciEliOutput.setBenifitAmt(200l);
			} else {
				ciEliOutput.setDenialReason("Not Eligiltiy");
				ciEliOutput.setPlanStatus("Rejected");
			}
		}

		if (ciEliOutput.getPlanStatus().equalsIgnoreCase("Approved")) {
			ciEliOutput.setCaseNumber(caseNumber);
			ciEliOutput.setPlanStartDate(LocalDate.now());
			ciEliOutput.setPlanEndDate(LocalDate.now().plusYears(2));
		}
		
		if (ciEliOutput.getPlanStatus().equalsIgnoreCase("Rejected")) {
			ciEliOutput.setCaseNumber(caseNumber);
		}

		return ciEliOutput;

	}

}
