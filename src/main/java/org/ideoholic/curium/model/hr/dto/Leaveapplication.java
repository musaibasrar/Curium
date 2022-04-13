package org.ideoholic.curium.model.hr.dto;

// default package
// Generated 26 Apr, 2018 8:10:08 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ideoholic.curium.model.employee.dto.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Leaveapplication generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hr_leaveapplication")
public class Leaveapplication implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idleaveapplication", unique = true, nullable = false)
	private Integer idleaveapplication;

	@Column(name = "idteacher")
	private Integer idteacher;

	@Temporal(TemporalType.DATE)
	@Column(name = "fromdate", length = 10)
	private Date fromdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "todate", length = 10)
	private Date todate;

	@Column(name = "leavetype", length = 45)
	private String leavetype;

	@Column(name = "reason", length = 500)
	private String reason;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@Column(name = "status", length = 45)
	private String status;

	@Column(name = "totalleaves")
	private Integer totalleaves;

	@Column(name = "dateofapply")
	private Date dateofapply;

	@Column(name = "dateofapproval")
	private Date dateofapproval;

	@ManyToOne
	@JoinColumn(name = "idteacher")
	private Teacher teacher;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}