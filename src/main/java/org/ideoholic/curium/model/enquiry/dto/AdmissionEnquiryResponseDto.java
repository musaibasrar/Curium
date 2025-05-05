package org.ideoholic.curium.model.enquiry.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AdmissionEnquiryResponseDto {
	
	private int id;
	private String name;
	private String gender;
	private String academicYear;
	private String caste;
	private String placeOfBirth;
	private String surName;
	private String previousClassPassed;
	private String previousSchoolName;
	private String religion;
	private String fathername;
	private String fatherQualification;
	private String mothername;
	private String motherQualification;
	private String admissionclass;
	private String brothereducation;
	private String sistereducation;
	private String occupation;
	private String dateofbirth;
	private String address;
	private String mobileno;
	private String notes;
	private AdmissionEnquiry admissionEnquiry;
	private List<AdmissionEnquiry> admissionEnquiryList;
	private boolean success;

}
