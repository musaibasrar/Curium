package com.model.student.dto;

// default package
// Generated 24 Jul, 2015 12:41:34 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.model.feescategory.dto.Feescategory;

/**
 * Studentfeesstructure generated by hbm2java
 */
@Entity
@Table(name = "fee_studentfeesstructure")
public class Studentfeesstructure implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sfsid;
	private Integer sid;
	private Integer idfeescategory;
	private Long feesamount;
	private String academicyear;
	private int branchid;
	private Integer concession;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfeescategory")
	private Feescategory feescategory;

	public Studentfeesstructure() {
	}

	public Studentfeesstructure(Integer sid, Integer idfeescategory,
			Long feesamount, String academicyear, int branchid, Integer concession) {
		this.sid = sid;
		this.idfeescategory = idfeescategory;
		this.feesamount = feesamount;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.concession = concession;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sfsid", unique = true, nullable = false)
	public Integer getSfsid() {
		return this.sfsid;
	}

	public void setSfsid(Integer sfsid) {
		this.sfsid = sfsid;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "idfeescategory")
	public Integer getIdfeescategory() {
		return this.idfeescategory;
	}

	public void setIdfeescategory(Integer idfeescategory) {
		this.idfeescategory = idfeescategory;
	}

	@Column(name = "feesamount", precision = 10, scale = 0)
	public Long getFeesamount() {
		return this.feesamount;
	}

	public void setFeesamount(Long feesamount) {
		this.feesamount = feesamount;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public Feescategory getFeescategory() {
		return feescategory;
	}

	public void setFeescategory(Feescategory feescategory) {
		this.feescategory = feescategory;
	}

	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

	@Column(name = "concession")
        public Integer getConcession() {
            return this.concession;
        }
    
        public void setConcession(Integer concession) {
            this.concession = concession;
        }
}
