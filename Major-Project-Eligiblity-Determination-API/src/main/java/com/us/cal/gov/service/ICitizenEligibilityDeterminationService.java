package com.us.cal.gov.service;

import com.us.cal.gov.bindings.CitizenEligibiltyOutputs;

public interface ICitizenEligibilityDeterminationService {
	
	public CitizenEligibiltyOutputs eligiblityDetermination(Long caseNumber);
}
