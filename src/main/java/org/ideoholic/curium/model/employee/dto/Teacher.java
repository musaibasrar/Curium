package org.ideoholic.curium.model.employee.dto;

// default package
// Generated 20 Jan, 2018 9:49:29 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Teacher generated by hbm2java
 */
@Entity
@Table(name = "teacher", uniqueConstraints = @UniqueConstraint(columnNames = "teacherexternalid"))
public class Teacher implements java.io.Serializable {

	private Integer tid;
	private String teachername;
	private String designation;
	private String subjectsteaching;
	private Date dateofjoining;
	private String classesteaching;
	private String salary;
	private String department;
	private String qualification;
	private String totalexperience;
	private String address;
	private String contactnumber;
	private String email;
	private String remarks;
	private String gender;
	private String teacherexternalid;
	private int branchid;
	private Date leavingdate;
	private Date joiningdate;
	private String bankname;
	private String bankbranch;
	private String bankifsc;
	private String accno;
	private String currentemployee;
	private int userid;
	private String employeephoto;
	private String employeedoc1;
	private String employeedoc2;
	private String employeedoc3;
	private String employeedoc4;
	private String employeedoc5;

	
	
	public Teacher() {
	}

	public Teacher(String teachername, String teacherexternalid) {
		this.teachername = teachername;
		this.teacherexternalid = teacherexternalid;
	}

	public Teacher(String teachername, String designation,
			String subjectsteaching, Date dateofjoining,
			String classesteaching, String salary, String department,
			String qualification, String totalexperience, String address,
			String contactnumber, String email, String remarks, String gender,
			String teacherexternalid, Date leavingdate, String bankname, String bankbranch, String bankifsc, String accno, String currentemployee, int userid,String employeephoto,
			String employeedoc1,String employeedoc2,String employeedoc3,String employeedoc4,String employeedoc5,Date joiningdate
) {
		this.teachername = teachername;
		this.designation = designation;
		this.subjectsteaching = subjectsteaching;
		this.dateofjoining = dateofjoining;
		this.classesteaching = classesteaching;
		this.salary = salary;
		this.department = department;
		this.qualification = qualification;
		this.totalexperience = totalexperience;
		this.address = address;
		this.contactnumber = contactnumber;
		this.email = email;
		this.remarks = remarks;
		this.gender = gender;
		this.teacherexternalid = teacherexternalid;
		this.leavingdate = leavingdate;
		this.bankname = bankname;
		this.bankbranch = bankbranch;
		this.bankifsc = bankifsc;
		this.accno = accno;
		this.currentemployee = currentemployee;
		this.userid = userid;
		this.employeephoto = employeephoto;
		this.employeedoc1 = employeedoc1;
		this.employeedoc2 = employeedoc2;
		this.employeedoc3 = employeedoc3;
		this.employeedoc4 = employeedoc4;
		this.employeedoc5 = employeedoc5;
		this.joiningdate = joiningdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tid", unique = true, nullable = false)
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@Column(name = "teachername", nullable = false, length = 100)
	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	@Column(name = "designation", length = 45)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "subjectsteaching", length = 500)
	public String getSubjectsteaching() {
		return this.subjectsteaching;
	}

	public void setSubjectsteaching(String subjectsteaching) {
		this.subjectsteaching = subjectsteaching;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofjoining", length = 10)
	public Date getDateofjoining() {
		return this.dateofjoining;
	}

	public void setDateofjoining(Date dateofjoining) {
		this.dateofjoining = dateofjoining;
	}

	@Column(name = "classesteaching", length = 200)
	public String getClassesteaching() {
		return this.classesteaching;
	}

	public void setClassesteaching(String classesteaching) {
		this.classesteaching = classesteaching;
	}

	@Column(name = "salary", length = 50)
	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Column(name = "department", length = 100)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "qualification", length = 45)
	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@Column(name = "totalexperience", length = 45)
	public String getTotalexperience() {
		return this.totalexperience;
	}

	public void setTotalexperience(String totalexperience) {
		this.totalexperience = totalexperience;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "contactnumber", length = 20)
	public String getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "remarks", length = 400)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "gender", length = 45)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "teacherexternalid", unique = true, nullable = false, length = 45)
	public String getTeacherexternalid() {
		return this.teacherexternalid;
	}

	public void setTeacherexternalid(String teacherexternalid) {
		this.teacherexternalid = teacherexternalid;
	}

	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

	public Date getLeavingdate() {
		return leavingdate;
	}

	public void setLeavingdate(Date leavingdate) {
		this.leavingdate = leavingdate;
	}
	
	public Date getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}

	public String getBankifsc() {
		return bankifsc;
	}

	public void setBankifsc(String bankifsc) {
		this.bankifsc = bankifsc;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getCurrentemployee() {
		return currentemployee;
	}

	public void setCurrentemployee(String currentemployee) {
		this.currentemployee = currentemployee;
	}
	
	public int getUserid() {
			return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getEmployeephoto() {
		return employeephoto;
	}

	public void setEmployeephoto(String employeephoto) {
		this.employeephoto = employeephoto;
	}
	
	public String getEmployeedoc1() {
		return employeedoc1;
	}

	public void setEmployeedoc1(String employeedoc1) {
		this.employeedoc1 = employeedoc1;
	}

	public String getEmployeedoc2() {
		return employeedoc2;
	}

	public void setEmployeedoc2(String employeedoc2) {
		this.employeedoc2 = employeedoc2;
	}

	public String getEmployeedoc3() {
		return employeedoc3;
	}

	public void setEmployeedoc3(String employeedoc3) {
		this.employeedoc3 = employeedoc3;
	}

	public String getEmployeedoc4() {
		return employeedoc4;
	}

	public void setEmployeedoc4(String employeedoc4) {
		this.employeedoc4 = employeedoc4;
	}

	public String getEmployeedoc5() {
		return employeedoc5;
	}

	public void setEmployeedoc5(String employeedoc5) {
		this.employeedoc5 = employeedoc5;
	}
}
