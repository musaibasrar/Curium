package org.ideoholic.curium.model.enquiry.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admissionenquiry")
public class AdmissionEnquiry implements java.io.Serializable{
	

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
	private Date dateofbirth;
	private String address;
	private String mobileno;
	private String notes;
	
	public AdmissionEnquiry() {
	}
	
	
	public AdmissionEnquiry(int id, String name, String gender, String caste, String placeOfBirth, String surName,
			String previousClassPassed, String previousSchoolName, String religion, String fathername,
			String fatherQualification, String mothername, String motherQualification, String admissionclass,
			String brothereducation, String sistereducation, String occupation, Date dateofbirth, String address,
			String mobileno, String academicYear, String notes) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.caste = caste;
		this.placeOfBirth = placeOfBirth;
		this.surName = surName;
		this.previousClassPassed = previousClassPassed;
		this.previousSchoolName = previousSchoolName;
		this.religion = religion;
		this.fathername = fathername;
		this.fatherQualification = fatherQualification;
		this.mothername = mothername;
		this.motherQualification = motherQualification;
		this.admissionclass = admissionclass;
		this.brothereducation = brothereducation;
		this.sistereducation = sistereducation;
		this.occupation = occupation;
		this.dateofbirth = dateofbirth;
		this.address = address;
		this.mobileno = mobileno;
		this.academicYear = academicYear;
		this.notes = notes;
	}


	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "fathername", length = 45)
	public String getFathername() {
		return fathername;
	}
	
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	@Column(name = "mothername", length = 45)
	public String getMothername() {
		return mothername;
	}
	
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	
	@Column(name = "admissionclass", length = 45)
	public String getAdmissionclass() {
		return admissionclass;
	}
	
	public void setAdmissionclass(String admissionclass) {
		this.admissionclass = admissionclass;
	}
	
	@Column(name = "dateofbirth", length = 45)
	public Date getDateofbirth() {
		return dateofbirth;
	}
	
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	@Column(name = "address", length = 1000)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mobileno", length = 45)
	public String getMobileno() {
		return mobileno;
	}
	
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	
	@Column(name = "gender", length = 45)
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "caste", length = 45)
	public String getCaste() {
		return caste;
	}


	public void setCaste(String caste) {
		this.caste = caste;
	}

	@Column(name = "placeofbirth", length = 45)
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}


	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	@Column(name = "surname", length = 45)
	public String getSurName() {
		return surName;
	}


	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Column(name = "reviousclasspassed", length = 45)
	public String getPreviousClassPassed() {
		return previousClassPassed;
	}


	public void setPreviousClassPassed(String previousClassPassed) {
		this.previousClassPassed = previousClassPassed;
	}

	@Column(name = "previousschoolname", length = 45)
	public String getPreviousSchoolName() {
		return previousSchoolName;
	}


	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}

	@Column(name = "religion", length = 45)
	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "fatherqualification", length = 45) 
	public String getFatherQualification() {
		return fatherQualification;
	}


	public void setFatherQualification(String fatherQualification) {
		this.fatherQualification = fatherQualification;
	}

	@Column(name = "motherqualification", length = 45) 
	public String getMotherQualification() {
		return motherQualification;
	}


	public void setMotherQualification(String motherQualification) {
		this.motherQualification = motherQualification;
	}

	@Column(name = "brothereducation", length = 45) 
	public String getBrothereducation() {
		return brothereducation;
	}


	public void setBrothereducation(String brothereducation) {
		this.brothereducation = brothereducation;
	}

	@Column(name = "sistereducation", length = 45) 
	public String getSistereducation() {
		return sistereducation;
	}


	public void setSistereducation(String sistereducation) {
		this.sistereducation = sistereducation;
	}

	@Column(name = "occupation", length = 45) 
	public String getOccupation() {
		return occupation;
	}


	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	@Column(name = "academicyear", length = 45) 
	public String getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}


	@Column(name = "notes", length = 1000)
	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

	

}
