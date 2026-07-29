package org.ideoholic.curium.model.ratingdetails.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AssessmentRank - Ranking for Holistic Development Assessment
 * Duplicated from ExamRank entity for independent assessment module
 */
@Entity
@Table(name = "assessmentrank")
public class AssessmentRank implements java.io.Serializable, Comparable<AssessmentRank> {

	private Integer id;
	private Integer sid;
	private Integer assessmentid;
	private float ratingobtained;
	private String academicyear;
	private String status;
	private int rank;
	private int branchid;
	private int userid;
	
	public AssessmentRank() {
	}
	
	public AssessmentRank(Integer id, Integer sid, Integer assessmentid, float ratingobtained, String academicyear, 
			String status, int rank, int branchid, int userid) {
		this.id = id;
		this.sid = sid;
		this.assessmentid = assessmentid;
		this.ratingobtained = ratingobtained;
		this.academicyear = academicyear;
		this.status = status;
		this.rank = rank;
		this.branchid = branchid;
		this.userid = userid;
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
	
	@Column(name = "sid")
	public Integer getSid() {
		return sid;
	}
	
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	@Column(name = "assessmentid")
	public Integer getAssessmentid() {
		return assessmentid;
	}
	
	public void setAssessmentid(Integer assessmentid) {
		this.assessmentid = assessmentid;
	}
	
	@Column(name = "ratingobtained")
	public float getRatingobtained() {
		return ratingobtained;
	}
	
	public void setRatingobtained(float ratingobtained) {
		this.ratingobtained = ratingobtained;
	}
	
	@Column(name = "academicyear")
	public String getAcademicyear() {
		return academicyear;
	}
	
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "rank")
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
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
	
	@Override
	public int compareTo(AssessmentRank assessmentRank) {
		float ratingObtained = ((AssessmentRank) assessmentRank).getRatingobtained();
		return Double.compare(ratingObtained, this.ratingobtained);
	}

}
