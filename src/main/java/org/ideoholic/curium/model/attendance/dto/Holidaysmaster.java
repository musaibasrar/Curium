package org.ideoholic.curium.model.attendance.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 16 Jan, 2018 9:20:32 PM by Hibernate Tools 4.0.0

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
 * Holidaysmaster generated by hbm2java
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "att_holidaysmaster")
public class Holidaysmaster implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "shid", unique = true, nullable = false)
	private Integer shid;

	@Temporal(TemporalType.DATE)
	@Column(name = "fromdate", nullable = false, length = 10)
	private Date fromdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "todate", nullable = false, length = 10)
	private Date todate;

	@Column(name = "holidayname", nullable = false, length = 40)
	private String holidayname;

	@Column(name = "academicyear", nullable = false, length = 10)
	private String academicyear;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}
