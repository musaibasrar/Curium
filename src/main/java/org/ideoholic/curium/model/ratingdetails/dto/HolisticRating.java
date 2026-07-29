package org.ideoholic.curium.model.ratingdetails.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HolisticRating - Grade-based ratings for Holistic Development Assessment
 * Duplicated from Marks entity with grade-based rating support
 * Stores both grade (A+, A, B+, etc.) and numeric value for comparison
 */
@Entity
@Table(name = "holisticrating")
public class HolisticRating implements java.io.Serializable {

	private Integer ratingid;
	private Integer sid;
	private Integer assessmentsubjectid;
	private Integer assessmentid;
	private float ratingvalue;
	private String ratinggrade;
	private String academicyear;
	private int branchid;
	private int userid;
	private Integer assessmentsubsubjectid;
	
	public HolisticRating() {
	}

	public HolisticRating(Integer sid, Integer assessmentsubjectid, Integer assessmentid,
			float ratingvalue, String ratinggrade, String academicyear, int branchid, int userid, Integer assessmentsubsubjectid) {
		this.sid = sid;
		this.assessmentsubjectid = assessmentsubjectid;
		this.assessmentid = assessmentid;
		this.ratingvalue = ratingvalue;
		this.ratinggrade = ratinggrade;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.userid = userid;
		this.assessmentsubsubjectid = assessmentsubsubjectid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ratingid", unique = true, nullable = false)
	public Integer getRatingid() {
		return this.ratingid;
	}

	public void setRatingid(Integer ratingid) {
		this.ratingid = ratingid;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "assessmentsubjectid")
	public Integer getAssessmentsubjectid() {
		return this.assessmentsubjectid;
	}

	public void setAssessmentsubjectid(Integer assessmentsubjectid) {
		this.assessmentsubjectid = assessmentsubjectid;
	}

	@Column(name = "assessmentid")
	public Integer getAssessmentid() {
		return this.assessmentid;
	}

	public void setAssessmentid(Integer assessmentid) {
		this.assessmentid = assessmentid;
	}

	@Column(name = "ratingvalue")
	public float getRatingvalue() {
		return this.ratingvalue;
	}

	public void setRatingvalue(float ratingvalue) {
		this.ratingvalue = ratingvalue;
	}

	@Column(name = "ratinggrade", length = 10)
	public String getRatinggrade() {
		return this.ratinggrade;
	}

	public void setRatinggrade(String ratinggrade) {
		this.ratinggrade = ratinggrade;
	}

	@Column(name = "academicyear")
	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "branchid")
	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Column(name = "assessmentsubsubjectid")
	public Integer getAssessmentsubsubjectid() {
		return assessmentsubsubjectid;
	}

	public void setAssessmentsubsubjectid(Integer assessmentsubsubjectid) {
		this.assessmentsubsubjectid = assessmentsubsubjectid;
	}
		
}
