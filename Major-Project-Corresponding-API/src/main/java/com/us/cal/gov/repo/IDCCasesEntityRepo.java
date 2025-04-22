package com.us.cal.gov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.DCCasesEntity;

public interface IDCCasesEntityRepo extends JpaRepository<DCCasesEntity, Long> {
	
	List<DCCasesEntity> findByCaseNumber(Long caseNumber);
}
