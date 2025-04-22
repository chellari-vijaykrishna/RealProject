package com.us.cal.gov.bindings;

import lombok.Data;

@Data
public class ElgibilityDetails {
	private  Long  caseNumber;
	private   String holderName;
	private    Long   holderSSN;
	private    String  planName;
	private    String  planStatus;
	private   Long  benifitAmt;
	private   String  bankName;
	private   Long  accountNumber;
}
