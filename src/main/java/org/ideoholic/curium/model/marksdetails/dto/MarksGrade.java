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
@Table(name = "markgrade")
public class MarksGrade implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "minpercentage", nullable = false)
	private int minpercentage;

	@Column(name = "maxpercentage", nullable = false)
	private int maxpercentage;

	@Column(name = "status", length = 45)
	private String status;

	@Column(name = "branchid")
	private Integer branchid;

}
