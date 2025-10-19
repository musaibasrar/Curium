package org.ideoholic.curium.model.marksdetails.dto;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "markgrade")
public class MarksGrade implements java.io.Serializable{
	private int id;
	private int minpercentage;
	private int maxpercentage;
    private String status;
    private int branchid;
	public MarksGrade() {
	}
	public MarksGrade(int id, int minpercentage, int maxpercentage, String status, int branchid) {
		this.id = id;
		this.minpercentage = minpercentage;
		this.maxpercentage = maxpercentage;
		this.status = status;
		this.branchid = branchid;
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
	@Column(name = "minpercentage")
	public int getMinpercentage() {
		return this.minpercentage;
	}
	public void setMinpercentage(int minpercentage) {
		this.minpercentage = minpercentage;
	}
	@Column(name = "maxpercentage")
	public int getMaxpercentage() {
		return this.maxpercentage;
	}
	public void setMaxpercentage(int maxpercentage) {
		this.maxpercentage = maxpercentage;
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
	}
