package com.vj.service;

import java.util.List;

import com.vj.entity.CaseWorkerEntity;

public interface ICaseWorkerMgmtService {

	public String registerCaseWorker(CaseWorkerEntity caseWorker);

	public List<CaseWorkerEntity> showAllCaseWorkers();

	public CaseWorkerEntity showWorkerById(Long caseWorkerId);

	public String updateWorker(CaseWorkerEntity caseWorker);
	
	public String deleteWorker(Long caseWorkerId);

	public String changeWorkerStatus(Long caseWorkerId, String status);

}
