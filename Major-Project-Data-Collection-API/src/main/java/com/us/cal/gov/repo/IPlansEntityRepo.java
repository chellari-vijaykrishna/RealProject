package com.us.cal.gov.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.PlansEntity;

public interface IPlansEntityRepo extends JpaRepository<PlansEntity, Long> {

}
