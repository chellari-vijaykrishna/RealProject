package com.us.cal.gov.service;

import java.util.List;

import com.us.cal.gov.bindings.ChildrenInputs;
import com.us.cal.gov.bindings.DCSummaryOutputs;
import com.us.cal.gov.bindings.EducationInputs;
import com.us.cal.gov.bindings.IncomeInputs;
import com.us.cal.gov.bindings.PlanSelections;

public interface IDataCollectionMgmtService {
	
    public Long generateCaseId(Long appId);
    
    public List<String> listAllPlans();
    
    public Long savePlanSelection(PlanSelections planSelections);
    
    public Long saveIncomeDetails(IncomeInputs incomeInputs);
    
    public Long saveEducationDeatails(EducationInputs educationInputs);
    
    public Long saveChildrenDetails(List<ChildrenInputs> childInputs);
    
    public DCSummaryOutputs generateSummaryReport(Long caseNumber);
    
    
}
