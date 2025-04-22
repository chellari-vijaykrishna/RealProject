package com.us.cal.gov.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.DCTriggerEntity;
import java.util.List;


public interface IDCTriggerEntityRepo extends JpaRepository<DCTriggerEntity, Long> {

	public List<DCTriggerEntity> findByTriggerStatus(String triggerStatus);
	
	public DCTriggerEntity findByCaseNumber(Long caseNumber);
}
