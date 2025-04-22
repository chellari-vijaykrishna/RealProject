package com.us.cal.gov.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MAJOR_PROJECT_ELIGIBLITY_DETAIL")
public class EligiblityDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long  edTraceId;
	private  Long  caseNumber;
	@Column(length = 30)
	private   String holderName;
	private    Long   holderSSN;
	@Column(length = 30)
	private    String  planName;
	@Column(length = 30)
	private    String  planStatus;
	private  LocalDate planStartDate;
	private  LocalDate  planEndDate;
	private   Long  benifitAmt;
	@Column(length = 30)
	private    String denialReason;
}
