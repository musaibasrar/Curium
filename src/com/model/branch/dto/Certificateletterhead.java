package com.model.branch.dto;

// default package
// Generated 23 May, 2018 11:42:50 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table(name = "certificateletterhead")
public class Certificateletterhead implements java.io.Serializable {

	private Integer id;
	private String institutionname;
	private String address;
	private String affiliation;
	private String logo;
	private String contactnumber;
	private String email;
	private Integer branchid;
	
	public Certificateletterhead() {
	}

	public Certificateletterhead(String institutionname, String address, String affiliation, String logo, String contactnumber, String email, int branchid) {
		this.institutionname = institutionname;
		this.address = address;
		this.affiliation = affiliation;
		this.logo = logo;
		this.contactnumber = contactnumber;
		this.email = email;
		this.branchid = branchid;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name = "institutionname", length = 100)
	public String getInstitutionname() {
		return institutionname;
	}

	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}


	@Column(name = "address", length = 500)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(name = "affiliation", length = 500)
	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getBranchid() {
		return branchid;
	}

	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
