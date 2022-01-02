package org.ideoholic.curium.model.student.dto;

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

import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student generated by hbm2java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "studentexternalid"))
public class Student implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sid", unique = true, nullable = false)
	private Integer sid;

	@Column(name = "name", nullable = false, length = 200)
	private String name;

	@Column(name = "classstudying", length = 45)
	private String classstudying;

	@Column(name = "classadmittedin", length = 45)
	private String classadmittedin;

	@Column(name = "age")
	private Integer age;

	@Column(name = "gender", length = 45)
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	private Date dateofbirth;

	@Column(name = "bloodgroup", length = 45)
	private String bloodgroup;

	@Column(name = "nationality", length = 45)
	private String nationality;

	@Column(name = "religion", length = 45)
	private String religion;

	@Column(name = "caste", length = 45)
	private String caste;

	@Temporal(TemporalType.DATE)
	@Column(name = "admissiondate", length = 10)
	private Date admissiondate;

	@Column(name = "admissionnumber", length = 20)
	private String admissionnumber;

	@Column(name = "mothertongue", length = 45)
	private String mothertongue;

	@Column(name = "Remarks", length = 500)
	private String remarks;

	@Column(name = "schoollastattended", length = 100)
	private String schoollastattended;

	@Column(name = "stdlaststudied", length = 45)
	private String stdlaststudied;

	@Temporal(TemporalType.DATE)
	@Column(name = "createddate", length = 10)
	private Date createddate;

	@Column(name = "archive")
	private Integer archive;

	@Column(name = "studentpic")
	private String studentpic;

	@Column(name = "studentexternalid", unique = true, nullable = false, length = 45)
	private String studentexternalid;
	
	@Column(name = "crecord", length = 45)
	private String crecord;

	@Temporal(TemporalType.DATE)
	@Column(name = "crecorddate", length = 10)
	private Date crecorddate;

	@Column(name = "placeofbirth", length = 100)
	private String placeofbirth;

	@Column(name = "nooftc")
	private Integer nooftc;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateoftc", length = 10)
	private Date dateoftc;

	@Column(name = "classonleaving", length = 45)
	private String classonleaving;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateleaving", length = 10)
	private Date dateleaving;

	@Column(name = "reasonleaving", length = 500)
	private String reasonleaving;

	@Column(name = "notcissued")
	private Integer notcissued;

	@Temporal(TemporalType.DATE)
	@Column(name = "datetcissued", length = 10)
	private Date datetcissued;

	@Column(name = "guardiandetails", length = 200)
	private String guardiandetails;

	@Column(name = "subsequentprogress", length = 500)
	private String subsequentprogress;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "languagesstudied", length = 80)
	private String languagesstudied;

	@Column(name = "instructionmediumlastschool", length = 45)
	private String instructionmediumlastschool;

	@Column(name = "passedout")
	private Integer passedout;

	@Column(name = "droppedout")
	private Integer droppedout;

	@Column(name = "leftout")
	private Integer leftout;

	@Column(name = "semester")
	private Integer semester;

	@Column(name = "stream", length = 25)
	private String stream;

	@Column(name = "mediumofinstruction", length = 15)
	private String mediumofinstruction;

	@Column(name = "previousschooltype", length = 30)
	private String previousschooltype;

	@Column(name = "previouschooladdress", length = 250)
	private String previouschooladdress;

	@Column(name = "urbanrural", length = 5)
	private String urbanrural;

	@Column(name = "studentscastecertno", length = 25)
	private String studentscastecertno;

	@Column(name = "studentscaste", length = 15)
	private String studentscaste;

	@Column(name = "socialcategory", length = 10)
	private String socialcategory;

	@Column(name = "belongtobpl")
	private Integer belongtobpl;

	@Column(name = "bplcardno")
	private String bplcardno;

	@Column(name = "bhagyalakshmibondnumber")
	private String bhagyalakshmibondnumber;

	@Column(name = "disabilitychild")
	private String disabilitychild;

	@Column(name = "sts")
	private Integer sts;

	@Column(name = "specialcategory", length = 25)
	private String specialcategory;

	@Column(name = "rte")
	private Integer rte;

	@Column(name = "bankname", length = 500)
	private String bankname;

	@Column(name = "bankbranch", length = 200)
	private String bankbranch;

	@Column(name = "accno", length = 50)
	private String accno;

	@Column(name = "bankifsc", length = 50)
	private String bankifsc;

	@Column(name = "userid")
	private int userid;
	
	private String lastcourse;
	private Integer totalmarks;
	private String lastfirstlanguage;
	private String lastsecondlanguage;
	private String lastschooladdress;
	private String registrationnumber;
	private Pudetails pudetails;
	private Degreedetails degreedetails;
}
