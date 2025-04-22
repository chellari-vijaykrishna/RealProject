package com.vj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vj.entity.CaseWorkerEntity;

public interface ICaseWorkerEntityRepo extends JpaRepository<CaseWorkerEntity, Long> {

}
