package org.ideoholic.curium.model.attendance.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 22 Jan, 2018 4:35:50 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Studentdailyattendance generated by hbm2java
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "att_studentdailyattendance")
public class Studentdailyattendance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "attendanceid", unique = true, nullable = false)
	private Integer attendanceid;

	@Column(name = "attendeeid", length = 45)
	private String attendeeid;

	@Column(name = "intime", length = 45)
	private String intime;

	@Column(name = "outtime", length = 45)
	private String outtime;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	private Date date;

	@Column(name = "attendancestatus", length = 15)
	private String attendancestatus;

	@Column(name = "academicyear", length = 10)
	private String academicyear;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}
