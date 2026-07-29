package org.ideoholic.curium.model.assessmentdetails.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HolisticAssessmentSchedule - Assessment Schedule Management
 * Duplicated from Examschedule entity for independent assessment module
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "holisticassessmentschedule")
public class HolisticAssessmentSchedule implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idassessmentschedule", unique = true, nullable = false)
	private Integer idassessmentschedule;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	private Date date;

	@Column(name = "starttime", length = 20)
	private String starttime;

	@Column(name = "endtime", length = 20)
	private String endtime;

	@Column(name = "subject", length = 30)
	private String subject;

	@Column(name = "assessmentname", length = 30)
	private String assessmentname;

	@Column(name = "classes", length = 45)
	private String classes;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}
