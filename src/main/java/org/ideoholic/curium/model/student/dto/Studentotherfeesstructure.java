package org.ideoholic.curium.model.student.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;

@Entity
@Table(name = "otherfee_studentfeesstructure")
public class Studentotherfeesstructure implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer sfsid;
	private Integer sid;
	private Integer idfeescategory;
	private Long feesamount;
	private Long feespaid;
	private String academicyear;
	private int branchid;
	private Integer concession;
	private Long waiveoff;
	private Integer totalinstallment;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idfeescategory")
	private OtherFeecategory otherfeescategory;
	private int userid;

	public Studentotherfeesstructure() {
	}

	public Studentotherfeesstructure(Integer sid, Integer idfeescategory,
			Long feesamount, Long feespaid, String academicyear, int branchid, Integer concession,  Long waiveoff, Integer totalinstallment, int userid) {
		this.sid = sid;
		this.idfeescategory = idfeescategory;
		this.feesamount = feesamount;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.concession = concession;
		this.feespaid = feespaid;
		this.waiveoff = waiveoff;
		this.totalinstallment = totalinstallment;
		this.userid = userid;
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

        @Column(name = "feespaid", precision = 10, scale = 0)
    	public Long getFeespaid() {
    		return this.feespaid;
    	}

    	public void setFeespaid(Long feespaid) {
    		this.feespaid = feespaid;
    	}

    	@Column(name = "waiveoff", precision = 10, scale = 0)
		public Long getWaiveoff() {
			return waiveoff;
		}

		public void setWaiveoff(Long waiveoff) {
			this.waiveoff = waiveoff;
		}

		@Column(name = "totalinstallment")
		public Integer getTotalinstallment() {
			return totalinstallment;
		}

		public void setTotalinstallment(Integer totalinstallment) {
			this.totalinstallment = totalinstallment;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
		this.userid = userid;
		}
		
		public OtherFeecategory getOtherfeescategory() {
			return otherfeescategory;
		}

		public void setOtherfeescategory(OtherFeecategory otherfeescategory) {
			this.otherfeescategory = otherfeescategory;
		}

}