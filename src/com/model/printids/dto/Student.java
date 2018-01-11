package com.model.printids.dto;

// default package
// Generated 12 Sep, 2014 6:04:00 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.model.parents.dto.Parents;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name = "student", catalog = "school")
public class Student implements java.io.Serializable {

	private Integer sid;
	private String name;
	private String classstudying;
	private String classadmittedin;
	private Integer age;
	private String gender;
	private Date dateofbirth;
	private String bloodgroup;
	private String nationality;
	private String religion;
	private String caste;
	private Date admissiondate;
	private String admissionnumber;
	private String mothertongue;
	private String remarks;
	private String schoollastattended;
	private String stdlaststudied;
	private Date createddate;
	private Integer archive;
	
	//private Parents parents;
	

	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public Student(String name, String classstudying, String classadmittedin,
			Integer age, String gender, Date dateofbirth, String bloodgroup,
			String nationality, String religion, String caste,
			Date admissiondate, String admissionnumber, String mothertongue,
			String remarks, String schoollastattended, String stdlaststudied, Date createddate, Integer archive) {
		this.name = name;
		this.classstudying = classstudying;
		this.classadmittedin = classadmittedin;
		this.age = age;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
		this.bloodgroup = bloodgroup;
		this.nationality = nationality;
		this.religion = religion;
		this.caste = caste;
		this.admissiondate = admissiondate;
		this.admissionnumber = admissionnumber;
		this.mothertongue = mothertongue;
		this.remarks = remarks;
		this.schoollastattended = schoollastattended;
		this.stdlaststudied = stdlaststudied;
		this.createddate = createddate;
		this.archive = archive;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "classstudying", length = 45)
	public String getClassstudying() {
		return this.classstudying;
	}

	public void setClassstudying(String classstudying) {
		this.classstudying = classstudying;
	}

	@Column(name = "classadmittedin", length = 45)
	public String getClassadmittedin() {
		return this.classadmittedin;
	}

	public void setClassadmittedin(String classadmittedin) {
		this.classadmittedin = classadmittedin;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "gender", length = 45)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Column(name = "bloodgroup", length = 45)
	public String getBloodgroup() {
		return this.bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	@Column(name = "nationality", length = 45)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "religion", length = 45)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "caste", length = 45)
	public String getCaste() {
		return this.caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "admissiondate", length = 10)
	public Date getAdmissiondate() {
		return this.admissiondate;
	}

	public void setAdmissiondate(Date admissiondate) {
		this.admissiondate = admissiondate;
	}

	@Column(name = "admissionnumber")
	public String getAdmissionnumber() {
		return this.admissionnumber;
	}

	public void setAdmissionnumber(String admissionnumber) {
		this.admissionnumber = admissionnumber;
	}

	@Column(name = "mothertongue", length = 45)
	public String getMothertongue() {
		return this.mothertongue;
	}

	public void setMothertongue(String mothertongue) {
		this.mothertongue = mothertongue;
	}

	@Column(name = "Remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "schoollastattended", length = 100)
	public String getSchoollastattended() {
		return this.schoollastattended;
	}

	public void setSchoollastattended(String schoollastattended) {
		this.schoollastattended = schoollastattended;
	}

	@Column(name = "stdlaststudied", length = 45)
	public String getStdlaststudied() {
		return this.stdlaststudied;
	}

	public void setStdlaststudied(String stdlaststudied) {
		this.stdlaststudied = stdlaststudied;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createddate", length = 10)
	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	@Column(name = "archive")
	public Integer getArchive() {
		return archive;
	}

	public void setArchive(Integer archive) {
		this.archive = archive;
	}

	

	
/*	public Parents getParents() {
		return parents;
	}

	public void setParents(Parents parents) {
		this.parents = parents;
	}
*/
}
