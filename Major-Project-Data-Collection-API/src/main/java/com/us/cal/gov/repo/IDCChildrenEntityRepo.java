package com.us.cal.gov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.DCChildrenEntity;

public interface IDCChildrenEntityRepo extends JpaRepository<DCChildrenEntity, Long> {
	public List<DCChildrenEntity> findByCaseNumber(Long caseNumber);

}
