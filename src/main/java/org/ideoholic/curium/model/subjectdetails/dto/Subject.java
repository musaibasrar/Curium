package org.ideoholic.curium.model.subjectdetails.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 15 Aug, 2016 12:22:36 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Subject generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "subid", unique = true, nullable = false)
	private Integer subid;

	@Column(name = "subjectname", length = 45)
	private String subjectname;

	@Column(name = "subjectid")
	private Integer subjectid;

	@Column(name = "minmarks")
	private float minmarks;

	@Column(name = "maxmarks")
	private float maxmarks;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "examname", length = 150)
	private String examname;

	@Column(name = "examclass", length = 150)
	private String examclass;

	@Column(name = "userid")
	private int userid;

}
