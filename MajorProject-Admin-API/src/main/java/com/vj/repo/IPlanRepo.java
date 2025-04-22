package com.vj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vj.entity.PlansEntity;

public interface IPlanRepo extends JpaRepository<PlansEntity, Long> {

}
