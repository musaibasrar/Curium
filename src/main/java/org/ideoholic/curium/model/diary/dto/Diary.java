package org.ideoholic.curium.model.diary.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ideoholic.curium.model.degreedetails.dto.Degreedetails;
import org.ideoholic.curium.model.pudetails.dto.Pudetails;
import org.ideoholic.curium.model.student.dto.Student;

import lombok.Data;

@Data
@Entity
@Table(name = "diary")
public class Diary implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "classsec", length = 45)
	private String classsec;
	
	@Column(name = "academicyear", length = 45)
	private String academicyear;
	
	@Column(name = "branchid", length = 45)
	private String branchid;
	
	@Column(name = "subject", length = 45)
	private String subject;
	
	@Column(name = "message", length = 1000)
	private String message;
	
	@Column(name = "startdate")
	private Date startdate;
	
	@Column(name = "enddate")
	private Date enddate;
	
	@Column(name = "createddate")
	private Date createddate;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "attachment1")
	private String attachment1;
	
	@Column(name = "attachment2")
	private String attachment2;
	
	@Column(name = "attachment3")
	private String attachment3;

	

}
