package com.vj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vj.entity.CaseWorkerEntity;
import com.vj.repo.ICaseWorkerEntityRepo;

@Service
public class ICaseWorkerMgmtServiceImpl implements ICaseWorkerMgmtService {

	@Autowired
	private ICaseWorkerEntityRepo caseWorkerRepo;

	@Override
	public String registerCaseWorker(CaseWorkerEntity caseWorker) {

//		Optional<CaseWorkerEntity> optCase = caseWorkerRepo.findById(caseWorker.getCaseWorkerId());
//		if (optCase.isPresent()) {
//			return "Already Registered";
//		} else {
//			Long status = caseWorkerRepo.save(caseWorker).getCaseWorkerId();
//			return "Registered with ::" + status;
//		}

		Long status = caseWorkerRepo.save(caseWorker).getCaseWorkerId();
		return "Registered with ::" + status;

	}

	@Override
	public List<CaseWorkerEntity> showAllCaseWorkers() {
		List<CaseWorkerEntity> caseList = caseWorkerRepo.findAll();
		return caseList;
	}

	@Override
	public CaseWorkerEntity showWorkerById(Long caseWorkerId) {

		/*
		 * Optional<CaseWorkerEntity> optCase = caseWorkerRepo.findById(caseWorkerId);
		 * if (optCase.isPresent()) { return optCase.get(); } return null;
		 */
		return caseWorkerRepo.findById(caseWorkerId).orElseThrow(() -> new IllegalArgumentException("NOT FOUND"));

	}

	@Override
	public String updateWorker(CaseWorkerEntity caseWorker) {
		Optional<CaseWorkerEntity> optCase = caseWorkerRepo.findById(caseWorker.getCaseWorkerId());
		if (optCase.isPresent()) {
			caseWorkerRepo.save(caseWorker);
			return "Registered with ::" + optCase.get().getCaseWorkerId();
		} else {
			return "CaseWorker Not Found ::";
		}
	}

	@Override
	public String deleteWorker(Long caseWorkerId) {
		Optional<CaseWorkerEntity> optCase = caseWorkerRepo.findById(caseWorkerId);
		if (optCase.isPresent()) {
			caseWorkerRepo.deleteById(caseWorkerId);
			return "Deleted :" + optCase.get().getCaseWorkerId();
		} else {
			return "CaseWorker Not Found ::";
		}
	}

	@Override
	public String changeWorkerStatus(Long caseWorkerId, String status) {
		Optional<CaseWorkerEntity> optCase = caseWorkerRepo.findById(caseWorkerId);
		if (optCase.isPresent()) {
			CaseWorkerEntity caseWorkerEnity = optCase.get();
			caseWorkerEnity.setActiveStatus(status);
			caseWorkerRepo.save(caseWorkerEnity);
			return "Status Changed ::";
		} else {
			return "CaseWorker Not Found ::";
		}
	}

}
