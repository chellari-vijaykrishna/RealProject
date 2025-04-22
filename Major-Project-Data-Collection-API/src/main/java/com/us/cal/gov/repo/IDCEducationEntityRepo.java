package com.us.cal.gov.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.DCEducationEntity;

public interface IDCEducationEntityRepo extends JpaRepository<DCEducationEntity, Long> {
	public DCEducationEntity findByCaseNumber(Long caseNumber);
}
