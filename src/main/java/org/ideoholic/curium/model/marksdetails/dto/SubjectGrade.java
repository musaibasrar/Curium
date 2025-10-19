package org.ideoholic.curium.model.marksdetails.dto;

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
@Table(name = "subjectgrade")
public class SubjectGrade implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "minmarks", nullable = false)
	private int minmarks;

	@Column(name = "maxmarks", nullable = false)
	private int maxmarks;

	@Column(name = "status", nullable = false, length = 45)
	private String status;

	@Column(name = "examid", length = 45)
	private String examid;

	@Column(name = "classsec", length = 50)
	private String classsec;

	@Column(name = "branchid")
	private Integer branchid;

}
