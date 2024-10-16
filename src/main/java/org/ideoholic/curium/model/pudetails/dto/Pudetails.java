package org.ideoholic.curium.model.pudetails.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// default package
// Generated 20 Jun, 2018 1:03:58 PM by Hibernate Tools 5.3.0.Beta2

/**
 * Pudetails generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pudetails")
public class Pudetails implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpudetails")
	private Integer idpudetails;

	@Column(name = "exampassedappearance")
	private Integer exampassedappearance;

	@Column(name = "exampassedyear", length = 45)
	private String exampassedyear;

	@Column(name = "exampassedregno", length = 45)
	private String exampassedregno;

	@Column(name = "exampassedresultwithclass", length = 100)
	private String exampassedresultwithclass;

	@Column(name = "secondlanguage", length = 45)
	private String secondlanguage;

	@Column(name = "aggregatemarkssslc", length = 45)
	private String aggregatemarkssslc;

	@Column(name = "optionalsubjects", length = 200)
	private String optionalsubjects;

	@Column(name = "compulsorysubjects", length = 500)
	private String compulsorysubjects;

	@Column(name = "sslcmediuminstruction", length = 45)
	private String sslcmediuminstruction;

	@Column(name = "pumediuminstruction", length = 45)
	private String pumediuminstruction;

	@Column(name = "userid")
	private int userid;

}
