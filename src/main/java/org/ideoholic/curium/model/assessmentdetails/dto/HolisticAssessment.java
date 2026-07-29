package org.ideoholic.curium.model.assessmentdetails.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HolisticAssessment - Holistic Development Assessment Master
 * Duplicated from Exams entity for independent assessment module
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "holisticassessment")
public class HolisticAssessment implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "assessmentid", unique = true, nullable = false)
	private Integer assessmentid;

	@Column(name = "assessmentname", length = 45)
	private String assessmentname;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}
