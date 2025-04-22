package com.us.cal.gov.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildrenInputs {
	private Long childrenId;
	private Long caseNumber;
	private LocalDate dob;
	private Long childSsn;
}
