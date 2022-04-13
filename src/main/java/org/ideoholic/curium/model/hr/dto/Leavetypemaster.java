package org.ideoholic.curium.model.hr.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 18 Apr, 2018 3:59:01 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Leavetypemaster generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hr_leavetypemaster")
public class Leavetypemaster implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idleavetypemaster", unique = true, nullable = false)
	private Integer idleavetypemaster;

	@Column(name = "leavetypename", length = 100)
	private String leavetypename;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}