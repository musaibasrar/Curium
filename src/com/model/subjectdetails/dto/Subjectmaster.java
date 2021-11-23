package com.model.subjectdetails.dto;

// default package
// Generated 15 Aug, 2016 12:22:36 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Subject generated by hbm2java
 */
@Entity
@Table(name = "subjectmaster")
public class Subjectmaster implements java.io.Serializable {

	private Integer subjectid;
	private String subjectname;
	private int branchid;
	private int userid;
	
	public Subjectmaster() {
	}

	public Subjectmaster(String subjectname, int branchid, 	int userid) {
		this.subjectname = subjectname;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "subjectid", unique = true, nullable = false)
	public Integer getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	@Column(name = "subjectname", length = 100)
	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public int getUserid() {
			return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
