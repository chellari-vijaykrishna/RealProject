package com.us.cal.gov.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.cal.gov.entity.EligiblityDetailsEntity;

public interface IElgibiltyDeterminationRepository extends JpaRepository<EligiblityDetailsEntity,Long> {

}
