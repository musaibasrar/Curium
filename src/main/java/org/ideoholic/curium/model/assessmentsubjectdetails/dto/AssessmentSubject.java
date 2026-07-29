package org.ideoholic.curium.model.assessmentsubjectdetails.dto;

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
 * AssessmentSubject - Subject details for Holistic Development Assessment
 * Duplicated from Subject entity for independent assessment module
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assessmentsubject")
public class AssessmentSubject implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "assessmentsubjectid", unique = true, nullable = false)
	private Integer assessmentsubjectid;

	@Column(name = "subjectname", length = 45)
	private String subjectname;

	@Column(name = "subjectid")
	private Integer subjectid;

	@Column(name = "minrating")
	private float minrating;

	@Column(name = "maxrating")
	private float maxrating;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "assessmentname", length = 150)
	private String assessmentname;

	@Column(name = "assessmentclass", length = 150)
	private String assessmentclass;

	@Column(name = "userid")
	private int userid;

}
