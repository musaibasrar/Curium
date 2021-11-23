package com.model.parents.dto;

// default package
// Generated 12 Sep, 2014 5:50:27 PM by Hibernate Tools 4.0.0

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.model.student.dto.Student;

/**
 * Parents generated by hbm2java
 */
@Entity
@Table(name = "parents")
public class Parents implements java.io.Serializable {

	private Integer pid;
	private String fathersname;
	private String mothersname;
	private String addresspermanent;
	private String addresstemporary;
	private String professsion;
	private String parentsannualincome;
	private Integer noofdependents;
	private Integer sid;
	private Integer tid;
	private String remarks;
	private String contactnumber;
	private String cocontactnumber;
	private String email;
	private Student student;
	private int branchid;
    private String fathersqualification;
    private String mothersqualification;
    private String fatheroccupation;
    private String motherscastecertno;
    private String fatherscaste;
    private String motherscaste;
    private int userid;
        
	public Parents() {
	}

	public Parents(String fathersname, String mothersname,
			String addresspermanent, String addresstemporary,
			String professsion, String parentsannualincome,
			Integer noofdependents, Integer sid, Integer tid, String remarks, String contactnumber, String cocontactnumber,
			int branchid, String fathersqualification, String mothersqualification,String fatheroccupation,String motherscastecertno,
			String fatherscaste,String motherscaste, int userid) {
		this.fathersname = fathersname;
		this.mothersname = mothersname;
		this.addresspermanent = addresspermanent;
		this.addresstemporary = addresstemporary;
		this.professsion = professsion;
		this.parentsannualincome = parentsannualincome;
		this.noofdependents = noofdependents;
		this.sid = sid;
		this.tid = tid;
		this.remarks = remarks;
		this.contactnumber = contactnumber;
		this.cocontactnumber = cocontactnumber;
		this.branchid = branchid;
		this.fathersqualification = fathersqualification;
		this.mothersqualification = mothersqualification;
		this.fatherscastecertno = fatherscastecertno;
		this.motherscastecertno = motherscastecertno;
		this.fatherscaste = fatherscaste;
		this.motherscaste = motherscaste;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pid", unique = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "fathersname", length = 100)
	public String getFathersname() {
		return this.fathersname;
	}

	public void setFathersname(String fathersname) {
		this.fathersname = fathersname;
	}

	@Column(name = "mothersname", length = 100)
	public String getMothersname() {
		return this.mothersname;
	}

	public void setMothersname(String mothersname) {
		this.mothersname = mothersname;
	}

	@Column(name = "addresspermanent", length = 500)
	public String getAddresspermanent() {
		return this.addresspermanent;
	}

	public void setAddresspermanent(String addresspermanent) {
		this.addresspermanent = addresspermanent;
	}

	@Column(name = "addresstemporary", length = 500)
	public String getAddresstemporary() {
		return this.addresstemporary;
	}

	public void setAddresstemporary(String addresstemporary) {
		this.addresstemporary = addresstemporary;
	}

	@Column(name = "professsion", length = 100)
	public String getProfesssion() {
		return this.professsion;
	}

	public void setProfesssion(String professsion) {
		this.professsion = professsion;
	}

	@Column(name = "parentsannualincome")
	public String getParentsannualincome() {
		return this.parentsannualincome;
	}

	public void setParentsannualincome(String parentsannualincome) {
		this.parentsannualincome = parentsannualincome;
	}

	@Column(name = "noofdependents")
	public Integer getNoofdependents() {
		return this.noofdependents;
	}

	public void setNoofdependents(Integer noofdependents) {
		this.noofdependents = noofdependents;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "tid")
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "cocontactnumber")
	public String getCocontactnumber() {
		return cocontactnumber;
	}

	public void setCocontactnumber(String cocontactnumber) {
		this.cocontactnumber = cocontactnumber;
	}

	@Column(name = "contactnumber")
	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	/**
	 * @return the email
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

	@Column(name = "fathersqualification")
    public String getFathersqualification() {
        return this.fathersqualification;
    }

    
    public void setFathersqualification(String fathersqualification) {
        this.fathersqualification = fathersqualification;
    }

    @Column(name = "mothersqualification")
    public String getMothersqualification() {
        return this.mothersqualification;
    }

    
    public void setMothersqualification(String mothersqualification) {
        this.mothersqualification = mothersqualification;
    }
    
	
	public String getFatherscastecertno() {
		return fatherscastecertno;
	}

	public void setFatherscastecertno(String fatherscastecertno) {
		this.fatherscastecertno = fatherscastecertno;
	}

	public String getMotherscastecertno() {
		return motherscastecertno;
	}

	public void setMotherscastecertno(String motherscastecertno) {
		this.motherscastecertno = motherscastecertno;
	}


	public String getFatherscaste() {
		return fatherscaste;
	}

	public void setFatherscaste(String fatherscaste) {
		this.fatherscaste = fatherscaste;
	}

	public String getMotherscaste() {
		return motherscaste;
	}

	public void setMotherscaste(String motherscaste) {
		this.motherscaste = motherscaste;
	}
	

	public int getUserid() {
			return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
