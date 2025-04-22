package com.us.cal.gov.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="MAJOR_PROJECT_CITIZEN_APP_REGISTER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenAppRegistrationEntity {
	
	@Id
	@SequenceGenerator(name = "app_seq",sequenceName = "app_id_seq",allocationSize = 1,initialValue = 10000)
	@GeneratedValue(generator = "app_seq",strategy = GenerationType.SEQUENCE)
	private Long appId;
	@Column(length = 20)
	private String fullName;
	@Column(length = 40)
	private String email;
	@Column(length = 1)
	private String gender;
	private LocalDate dob;
	@Column(length = 12)
	private Long phoneNumber ;
	@Column(length = 9)
	private Long citizenSsn;
	@Column(length = 20)
	private String stateName;
	
	//MetaData
	@Column(length = 20)
	private String createdBy;
	@Column(length = 20)
	private String updatedBy;
	
	@Column(insertable = true,updatable = false,length = 20)
	private LocalDate createdDate;
	@Column(insertable = false,updatable = true,length = 20)
	private LocalDate updatedDate;
	
}
