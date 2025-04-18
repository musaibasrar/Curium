package org.ideoholic.curium.model.enquiry.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admissionenquiry")
public class AdmissionEnquiry implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "branchid")
	private int branchId;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "name", length = 45)
	private String name;
	
	@Column(name = "gender", length = 45)
	private String gender;
	
	@Column(name = "academicyear", length = 45) 
	private String academicYear;
	
	@Column(name = "caste", length = 45)
	private String caste;
	
	@Column(name = "placeofbirth", length = 45)
	private String placeOfBirth;
	
	@Column(name = "surname", length = 45)
	private String surName;
	
	@Column(name = "reviousclasspassed", length = 45)
	private String previousClassPassed;
	
	@Column(name = "previousschoolname", length = 45)
	private String previousSchoolName;
	
	@Column(name = "religion", length = 45)
	private String religion;
	
	@Column(name = "fathername", length = 45)
	private String fathername;
	
	@Column(name = "fatherqualification", length = 45) 
	private String fatherQualification;
	
	@Column(name = "mothername", length = 45)
	private String mothername;
	
	@Column(name = "motherqualification", length = 45) 
	private String motherQualification;
	
	@Column(name = "admissionclass", length = 45)
	private String admissionclass;
	
	@Column(name = "brothereducation", length = 45) 
	private String brothereducation;
	
	@Column(name = "sistereducation", length = 45) 
	private String sistereducation;
	
	@Column(name = "occupation", length = 45) 
	private String occupation;
	
	@Column(name = "dateofbirth", length = 45)
	private Date dateofbirth;
	
	@Column(name = "address", length = 1000)
	private String address;
	
	@Column(name = "mobileno", length = 45)
	private String mobileno;
	
	@Column(name = "notes", length = 1000)
	private String notes;
	
	

}
