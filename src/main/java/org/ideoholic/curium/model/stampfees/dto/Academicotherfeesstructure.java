package org.ideoholic.curium.model.stampfees.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otherfee_academicfeesstructure")
public class Academicotherfeesstructure implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feesstructureid", unique = true, nullable = false)
	private Integer feesstructureid;

	@Column(name = "sid", nullable = false)
	private Integer sid;

	@Column(name = "totalfees", length = 45)
	private String totalfees;

	@Column(name = "paidfees", length = 45)
	private String paidfees;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@Column(name = "branchid")
	private Integer branchid;
	
	@Column(name = "userid")
	private Integer userid;
}