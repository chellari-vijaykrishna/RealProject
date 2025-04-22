package com.us.cal.gov.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenAppRegisterInputs {
	 private   String  fullName;
	 private   String email;
	 private   String  gender;
	 private    Long  phoneNumber;
	 private    Long  citizenSsn;
	 private   LocalDate  dob;
}
