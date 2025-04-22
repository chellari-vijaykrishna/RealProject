package com.us.cal.gov.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MAJOR_PROJECT_DC_EDUCATION")
public class DCEducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long educationId;
	private Long caseNumber;
	private String higestQualification; 
	private Long passOutYear;
	
}
