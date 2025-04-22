package com.us.cal.gov.bindings;

import java.util.List;

import lombok.Data;

@Data
public class DCSummaryOutputs {
	private String planName;
	private CitizenAppRegisterInputs citizenAppRegInputs;
	private EducationInputs educationInputs;
	private IncomeInputs incomeInputs;
	private List<ChildrenInputs> childrenInputs;
}
