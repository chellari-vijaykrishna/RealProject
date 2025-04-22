package com.us.cal.gov.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MAJOR_PROJECT_DC_CHILDREN")
public class DCChildrenEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long childrenId;
	private Long caseNumber;
	private LocalDate dob;
	private Long childSsn;
}
