package com.us.cal.gov.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MAJOR_PROJECT_PLANS")
public class PlansEntity {
	
	@Id
	@SequenceGenerator(name = "seq_gen2",sequenceName = "seq2",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "seq_gen2",strategy = GenerationType.AUTO)
	private Long planId;
	@Column(length = 20)
	private String planName;
	@Column(length = 20)
	private String activeSwitch;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	@Column(length = 100)
	private String planDescription;
	private Long catagoryId;
	
	
	@Column(length = 20)
	private String createdBy;
	@Column(length = 20)
	private String updatedBy;
	@Column(updatable = false,insertable = true)
	private LocalDate createdDate;
	@Column(insertable = false, updatable = true)
	private LocalDate updatedDate;
}
