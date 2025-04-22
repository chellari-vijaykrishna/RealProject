package com.us.cal.gov.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplicationRegistrationsInputs {
	
	private String fullName;
	private String email;
	private String gender;
	private Long phoneNo ;
	private Long citizenSsn;
	private String stateName;
	private LocalDate dob;

}
