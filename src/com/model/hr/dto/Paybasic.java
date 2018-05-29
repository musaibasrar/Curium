package com.model.hr.dto;

// default package
// Generated 23 Apr, 2018 7:33:23 AM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.model.employee.dto.Teacher;

/**
 * Paybasic generated by hbm2java
 */
@Entity
@Table(name = "hr_paybasic")
public class Paybasic implements java.io.Serializable {

	private Integer idpaybasic;
	private int idteacher;
	private BigDecimal basicpay;
	private String paymenttype;
	private String accountno;
	private String overtime;
	private String academicyear;
	private Teacher teacher;
	private int branchid;
	
	public Paybasic() {
	}

	public Paybasic(int idteacher) {
		this.idteacher = idteacher;
	}

	public Paybasic(int idteacher, BigDecimal basicpay, String paymenttype,
			String accountno, String overtime, String academicyear, Teacher teacher,int branchid) {
		this.idteacher = idteacher;
		this.basicpay = basicpay;
		this.paymenttype = paymenttype;
		this.accountno = accountno;
		this.overtime = overtime;
		this.academicyear = academicyear;
		this.teacher = teacher;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpaybasic", unique = true, nullable = false)
	public Integer getIdpaybasic() {
		return this.idpaybasic;
	}

	public void setIdpaybasic(Integer idpaybasic) {
		this.idpaybasic = idpaybasic;
	}

	@Column(name = "idteacher", nullable = false)
	public int getIdteacher() {
		return this.idteacher;
	}

	public void setIdteacher(int idteacher) {
		this.idteacher = idteacher;
	}

	@Column(name = "basicpay", precision = 20, scale = 4)
	public BigDecimal getBasicpay() {
		return this.basicpay;
	}

	public void setBasicpay(BigDecimal basicpay) {
		this.basicpay = basicpay;
	}

	@Column(name = "paymenttype", length = 45)
	public String getPaymenttype() {
		return this.paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	@Column(name = "accountno", length = 40)
	public String getAccountno() {
		return this.accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	@Column(name = "overtime", length = 10)
	public String getOvertime() {
		return this.overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}
}
