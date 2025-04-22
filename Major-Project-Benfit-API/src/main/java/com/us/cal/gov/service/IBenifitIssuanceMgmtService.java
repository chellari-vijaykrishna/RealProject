package com.us.cal.gov.service;

import org.springframework.batch.core.JobExecution;

public interface IBenifitIssuanceMgmtService {
    public     JobExecution   sendAmountToBenificries()throws Exception;
}
