package org.ideoholic.curium.model.marksdetails.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "examrank")
public class ExamRank implements java.io.Serializable,Comparable<ExamRank>{

	private Integer id;
	private Integer sid;
	private Integer examid;
	private Integer marksobtained;
	private String academicyear;
	private String status;
	private int rank;
	private int branchid;
	private int userid;
	public ExamRank() {
	}
	public ExamRank(Integer id, Integer sid, Integer examid, Integer marksobtained, String academicyear, String status,
			int rank, int branchid, int userid) {
		this.id = id;
		this.sid = sid;
		this.examid = examid;
		this.marksobtained = marksobtained;
		this.academicyear = academicyear;
		this.status = status;
		this.rank = rank;
		this.branchid = branchid;
		this.userid = userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getExamid() {
		return examid;
	}
	public void setExamid(Integer examid) {
		this.examid = examid;
	}
	public Integer getMarksobtained() {
		return marksobtained;
	}
	public void setMarksobtained(Integer marksobtained) {
		this.marksobtained = marksobtained;
	}
	public String getAcademicyear() {
		return academicyear;
	}
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
	@Override
	public int compareTo(ExamRank examRank) {
		 int marksObtained = ((ExamRank) examRank).getMarksobtained();
	        return Double.compare(marksObtained, this.marksobtained);
	}
	

}
