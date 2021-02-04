package com.model.student.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 14 Feb, 2018 12:05:32 AM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.model.degreedetails.dto.Degreedetails;
import com.model.pudetails.dto.Pudetails;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "studentexternalid"))
public class Student implements java.io.Serializable {

	private Integer sid;
	private String name;
	private String classstudying;
	private String classadmittedin;
	private Integer age;
	private String gender;
	private Date dateofbirth;
	private String bloodgroup;
	private String nationality;
	private String religion;
	private String caste;
	private Date admissiondate;
	private String admissionnumber;
	private String mothertongue;
	private String remarks;
	private String schoollastattended;
	private String stdlaststudied;
	private Date createddate;
	private Integer archive;
	private String studentpic;
	private String studentexternalid;
	private String crecord;
	private Date crecorddate;
	private String placeofbirth;
	private Integer nooftc;
	private Date dateoftc;
	private String classonleaving;
	private Date dateleaving;
	private String reasonleaving;
	private Integer notcissued;
	private Date datetcissued;
	private String guardiandetails;
	private int branchid;
	private String languagesstudied;
	private String instructionmediumlastschool;
	private Pudetails pudetails;
	private Degreedetails degreedetails;
	private Integer passedout;
	private Integer droppedout;
	private Integer leftout;
	
	private Integer semester;
	private String stream;
	private String mediumofinstruction;
	private String previousschooltype;
	private String previouschooladdress;
	private String urbanrural;
	private String studentscastecertno;
	private String studentscaste;
	private String socialcategory;
	private Integer belongtobpl;
	private Integer sts;
	private Integer rte;
	private String bankbranch;
	private String accno;
	private String bankifsc;
	
	//for mess private String bankname;
	private String college;
	
	//for mess private String bplcardno;
	private String breakfast;

	//for mess private String bhagyalakshmibondnumber;
	private String lunch;
	
	//for mess private String specialcategory;
	private String dinner;
	
	//for mess private String disabilitychild;
	private String staytype;
	
	//for mess private String subsequentprogress;
	private String campus;
	
	public Student() {
	}

	public Student(String name, String studentexternalid) {
		this.name = name;
		this.studentexternalid = studentexternalid;
	}

	public Student(String name, String classstudying, String classadmittedin,
			Integer age, String gender, Date dateofbirth, String bloodgroup,
			String nationality, String religion, String caste,
			Date admissiondate, String admissionnumber, String mothertongue,
			String remarks, String schoollastattended, String stdlaststudied,
			Date createddate, Integer archive, String studentpic,
			String studentexternalid, String crecord, Date crecorddate,
			String placeofbirth, Integer nooftc, Date dateoftc,
			String classonleaving, Date dateleaving, String reasonleaving,
			Integer notcissued, Date datetcissued, String guardiandetails, int branchid,
			String languagesstudied, String instructionmediumlastschool,
			Integer passedout, Integer droppedout, Integer leftout, Integer semester,String stream,String mediumofinstruction,
			 String previousschooltype,String previouschooladdress,String urbanrural,String studentscastecertno,String studentscaste,
			 String socialcategory,Integer belongtobpl,String breakfast,String lunch,String dinner,String staytype,String campus,
			 Integer sts, Integer rte, String college, String bankbranch, String accno, String bankifsc) {
		this.name = name;
		this.classstudying = classstudying;
		this.classadmittedin = classadmittedin;
		this.age = age;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
		this.bloodgroup = bloodgroup;
		this.nationality = nationality;
		this.religion = religion;
		this.caste = caste;
		this.admissiondate = admissiondate;
		this.admissionnumber = admissionnumber;
		this.mothertongue = mothertongue;
		this.remarks = remarks;
		this.schoollastattended = schoollastattended;
		this.stdlaststudied = stdlaststudied;
		this.createddate = createddate;
		this.archive = archive;
		this.studentpic = studentpic;
		this.studentexternalid = studentexternalid;
		this.crecord = crecord;
		this.crecorddate = crecorddate;
		this.placeofbirth = placeofbirth;
		this.nooftc = nooftc;
		this.dateoftc = dateoftc;
		this.classonleaving = classonleaving;
		this.dateleaving = dateleaving;
		this.reasonleaving = reasonleaving;
		this.notcissued = notcissued;
		this.datetcissued = datetcissued;
		this.guardiandetails = guardiandetails;
		this.branchid = branchid;
		this.languagesstudied = languagesstudied;
		this.instructionmediumlastschool = instructionmediumlastschool;
		this.passedout = passedout;
		this.droppedout = droppedout;
		this.leftout = leftout;
		this.semester = semester;
		this.stream = stream;
		this.mediumofinstruction = mediumofinstruction;
		this.previousschooltype = previousschooltype;
		this.previouschooladdress = previouschooladdress;
		this.urbanrural = urbanrural;
		this.studentscastecertno = studentscastecertno;
		this.studentscaste = studentscaste;
		this.socialcategory = socialcategory;
		this.belongtobpl = belongtobpl;
		this.sts=sts;
		this.rte=rte;
		this.college=college;
		this.bankbranch=bankbranch;
		this.accno=accno;
		this.bankifsc=bankifsc;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.staytype = staytype;
		this.campus = campus;

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "classstudying", length = 45)
	public String getClassstudying() {
		return this.classstudying;
	}

	public void setClassstudying(String classstudying) {
		this.classstudying = classstudying;
	}

	@Column(name = "classadmittedin", length = 45)
	public String getClassadmittedin() {
		return this.classadmittedin;
	}

	public void setClassadmittedin(String classadmittedin) {
		this.classadmittedin = classadmittedin;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "gender", length = 45)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Column(name = "bloodgroup", length = 45)
	public String getBloodgroup() {
		return this.bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	@Column(name = "nationality", length = 45)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "religion", length = 45)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "caste", length = 45)
	public String getCaste() {
		return this.caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "admissiondate", length = 10)
	public Date getAdmissiondate() {
		return this.admissiondate;
	}

	public void setAdmissiondate(Date admissiondate) {
		this.admissiondate = admissiondate;
	}

	@Column(name = "admissionnumber", length = 20)
	public String getAdmissionnumber() {
		return this.admissionnumber;
	}

	public void setAdmissionnumber(String admissionnumber) {
		this.admissionnumber = admissionnumber;
	}

	@Column(name = "mothertongue", length = 45)
	public String getMothertongue() {
		return this.mothertongue;
	}

	public void setMothertongue(String mothertongue) {
		this.mothertongue = mothertongue;
	}

	@Column(name = "Remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "schoollastattended", length = 100)
	public String getSchoollastattended() {
		return this.schoollastattended;
	}

	public void setSchoollastattended(String schoollastattended) {
		this.schoollastattended = schoollastattended;
	}

	@Column(name = "stdlaststudied", length = 45)
	public String getStdlaststudied() {
		return this.stdlaststudied;
	}

	public void setStdlaststudied(String stdlaststudied) {
		this.stdlaststudied = stdlaststudied;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createddate", length = 10)
	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	@Column(name = "archive")
	public Integer getArchive() {
		return this.archive;
	}

	public void setArchive(Integer archive) {
		this.archive = archive;
	}

	@Column(name = "studentpic")
	public String getStudentpic() {
		return this.studentpic;
	}

	public void setStudentpic(String studentpic) {
		this.studentpic = studentpic;
	}

	@Column(name = "studentexternalid", unique = true, nullable = false, length = 45)
	public String getStudentexternalid() {
		return this.studentexternalid;
	}

	public void setStudentexternalid(String studentexternalid) {
		this.studentexternalid = studentexternalid;
	}

	@Column(name = "crecord", length = 45)
	public String getCrecord() {
		return this.crecord;
	}

	public void setCrecord(String crecord) {
		this.crecord = crecord;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "crecorddate", length = 10)
	public Date getCrecorddate() {
		return this.crecorddate;
	}

	public void setCrecorddate(Date crecorddate) {
		this.crecorddate = crecorddate;
	}

	@Column(name = "placeofbirth", length = 100)
	public String getPlaceofbirth() {
		return this.placeofbirth;
	}

	public void setPlaceofbirth(String placeofbirth) {
		this.placeofbirth = placeofbirth;
	}

	@Column(name = "nooftc")
	public Integer getNooftc() {
		return this.nooftc;
	}

	public void setNooftc(Integer nooftc) {
		this.nooftc = nooftc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateoftc", length = 10)
	public Date getDateoftc() {
		return this.dateoftc;
	}

	public void setDateoftc(Date dateoftc) {
		this.dateoftc = dateoftc;
	}

	@Column(name = "classonleaving", length = 45)
	public String getClassonleaving() {
		return this.classonleaving;
	}

	public void setClassonleaving(String classonleaving) {
		this.classonleaving = classonleaving;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateleaving", length = 10)
	public Date getDateleaving() {
		return this.dateleaving;
	}

	public void setDateleaving(Date dateleaving) {
		this.dateleaving = dateleaving;
	}

	@Column(name = "reasonleaving", length = 500)
	public String getReasonleaving() {
		return this.reasonleaving;
	}

	public void setReasonleaving(String reasonleaving) {
		this.reasonleaving = reasonleaving;
	}

	@Column(name = "notcissued")
	public Integer getNotcissued() {
		return this.notcissued;
	}

	public void setNotcissued(Integer notcissued) {
		this.notcissued = notcissued;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "datetcissued", length = 10)
	public Date getDatetcissued() {
		return this.datetcissued;
	}

	public void setDatetcissued(Date datetcissued) {
		this.datetcissued = datetcissued;
	}

	@Column(name = "guardiandetails", length = 200)
	public String getGuardiandetails() {
		return this.guardiandetails;
	}

	public void setGuardiandetails(String guardiandetails) {
		this.guardiandetails = guardiandetails;
	}

	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

    
    public Pudetails getPudetails() {
        return this.pudetails;
    }

    
    public void setPudetails(Pudetails pudetails) {
        this.pudetails = pudetails;
    }

    
    public String getLanguagesstudied() {
        return this.languagesstudied;
    }

    
    public void setLanguagesstudied(String languagesstudied) {
        this.languagesstudied = languagesstudied;
    }

    
    public String getInstructionmediumlastschool() {
        return this.instructionmediumlastschool;
    }

    
    public void setInstructionmediumlastschool(String instructionmediumlastschool) {
        this.instructionmediumlastschool = instructionmediumlastschool;
    }

    
    public Degreedetails getDegreedetails() {
        return this.degreedetails;
    }

    
    public void setDegreedetails(Degreedetails degreedetails) {
        this.degreedetails = degreedetails;
    }
    
    @Column(name = "passedout")
	public Integer getPassedout() {
		return passedout;
	}

	public void setPassedout(Integer passedout) {
		this.passedout = passedout;
	}

	@Column(name = "droppedout")
	public Integer getDroppedout() {
		return droppedout;
	}

	public void setDroppedout(Integer droppedout) {
		this.droppedout = droppedout;
	}

	@Column(name = "leftout")
	public Integer getLeftout() {
		return leftout;
	}

	public void setLeftout(Integer leftout) {
		this.leftout = leftout;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getMediumofinstruction() {
		return mediumofinstruction;
	}

	public void setMediumofinstruction(String mediumofinstruction) {
		this.mediumofinstruction = mediumofinstruction;
	}

	public String getPreviousschooltype() {
		return previousschooltype;
	}

	public void setPreviousschooltype(String previousschooltype) {
		this.previousschooltype = previousschooltype;
	}

	public String getPreviouschooladdress() {
		return previouschooladdress;
	}

	public void setPreviouschooladdress(String previouschooladdress) {
		this.previouschooladdress = previouschooladdress;
	}

	public String getUrbanrural() {
		return urbanrural;
	}

	public void setUrbanrural(String urbanrural) {
		this.urbanrural = urbanrural;
	}

	public String getStudentscastecertno() {
		return studentscastecertno;
	}

	public void setStudentscastecertno(String studentscastecertno) {
		this.studentscastecertno = studentscastecertno;
	}

	public String getStudentscaste() {
		return studentscaste;
	}

	public void setStudentscaste(String studentscaste) {
		this.studentscaste = studentscaste;
	}

	public String getSocialcategory() {
		return socialcategory;
	}

	public void setSocialcategory(String socialcategory) {
		this.socialcategory = socialcategory;
	}

	public Integer getBelongtobpl() {
		return belongtobpl;
	}

	public void setBelongtobpl(Integer belongtobpl) {
		this.belongtobpl = belongtobpl;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public Integer getRte() {
		return rte;
	}

	public void setRte(Integer rte) {
		this.rte = rte;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getBankifsc() {
		return bankifsc;
	}

	public void setBankifsc(String bankifsc) {
		this.bankifsc = bankifsc;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public String getStaytype() {
		return staytype;
	}

	public void setStaytype(String staytype) {
		this.staytype = staytype;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	
	
}
