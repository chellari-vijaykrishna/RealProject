package com.us.cal.gov.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.CitizenAppRegistrationEntity;

public interface ICitizenAppRegisterRepo extends JpaRepository<CitizenAppRegistrationEntity,Long> {

}
