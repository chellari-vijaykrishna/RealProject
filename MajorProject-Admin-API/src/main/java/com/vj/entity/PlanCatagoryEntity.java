package com.vj.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="MAJOR_PROJECT_PLAN_CATAGORY")
@Data
public class PlanCatagoryEntity {
	
	@Id
	@SequenceGenerator(name="seq3",sequenceName = "cata_seq1",initialValue = 1,allocationSize =1)
	@GeneratedValue(generator ="seq3",strategy = GenerationType.SEQUENCE)
	@Column(name="CATAGORY_ID")
	private Integer catagoryId;
	
	@Column(name = "CATAGORY_NAME", length = 20)
	private String catagoryName;
	
	@Column(name="ACTIVE_STATUS",length = 10)
	private String activeStatus;
	
	@Column(name = "CREATED_BY",length = 20)
	private String createdBy;
	
	@Column(name = "UPDATED_BY",length = 20)
	private String updatedBy;
	
	@Column(name="CREATED_DATE",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name="UPDATED_DATE",insertable = false,updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	

}
