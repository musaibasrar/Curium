package org.ideoholic.curium.model.marksdetails.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjectgrade")
public class SubjectGrade implements java.io.Serializable {
	private int id;
	private int minmarks;
	private int maxmarks;
    private String status;
    private int branchid;
    private int examid;
	
    public SubjectGrade() {
	}
	
    public SubjectGrade(int id, int minmarks, int maxpmarks, String status, int branchid, int examid) {
		this.id = id;
		this.minmarks = minmarks;
		this.maxmarks = maxpmarks;
		this.status = status;
		this.branchid = branchid;
		this.examid = examid;
	}
	
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "minmarks")
	public int getMinmarks() {
		return this.minmarks;
	}
	public void setMinmarks(int minmarks) {
		this.minmarks = minmarks;
	}
	@Column(name = "maxmarks")
	public int getMaxmarks() {
		return this.maxmarks;
	}
	public void setMaxmarks(int maxmarks) {
		this.maxmarks = maxmarks;
	}
	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "branchid")
	public int getBranchid() {
		return this.branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	
	@Column(name = "examid")
	public int getExamid() {
		return this.minmarks;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	
	}
