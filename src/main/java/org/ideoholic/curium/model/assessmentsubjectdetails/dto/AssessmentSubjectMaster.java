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
 * AssessmentSubjectMaster - Subject Master for Holistic Development Assessment
 * Duplicated from Subjectmaster entity for independent assessment module
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assessmentsubjectmaster")
public class AssessmentSubjectMaster implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "subjectid", unique = true, nullable = false)
	private Integer subjectid;

	@Column(name = "subjectname", length = 100)
	private String subjectname;
	
	@Column(name = "category", length = 100)
	private String category;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}
