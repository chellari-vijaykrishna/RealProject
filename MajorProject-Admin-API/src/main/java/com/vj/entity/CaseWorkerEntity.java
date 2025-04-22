package com.vj.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "MAJOR_PROJECT_CASE_WORKER")
public class CaseWorkerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseWorkerId;
	@Column(length = 30)
	private String caseWorkerFullName;
	@Column(length = 40)
	private String caseWorkerEmail;
	@Column(length = 20)
	private String password;
	@Column(length = 10)
	private String gender;
	@Column(length = 9)
	private Long ssn;
	private LocalDate dob;
	
	private String activeStatus;
	
	//MetaData
	@Column(length = 20)
	private String createdBy;
	
	@Column(length = 20)
	private String updatedBy;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(insertable = false,updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedDate;

}
