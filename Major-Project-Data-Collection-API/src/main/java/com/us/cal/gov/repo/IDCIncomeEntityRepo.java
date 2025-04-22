package com.us.cal.gov.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.DCIncomeEntity;

public interface IDCIncomeEntityRepo extends JpaRepository<DCIncomeEntity, Long> {
	public DCIncomeEntity findByCaseNumber(Long caseNumber);
}
