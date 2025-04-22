package com.us.cal.gov.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenEligibiltyOutputs {
	private Long caseNumber;
	private String holderName;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Long benifitAmt;
	private String denialReason;
}
