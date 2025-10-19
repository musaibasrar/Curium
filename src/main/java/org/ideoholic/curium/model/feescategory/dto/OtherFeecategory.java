package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otherfee_feescategory")
public class OtherFeecategory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfeescategory", unique = true, nullable = false)
	private Integer idfeescategory;

	@Column(name = "feescategoryname", length = 150)
	private String feescategoryname;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "particularname", length = 150)
	private String particularname;

	@Column(name = "academicyear", length = 20)
	private String academicyear;

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "userid")
	private Integer userid;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "otherfeescategory")
	private List<Studentotherfeesstructure> otherfeeStudentfeesstructureList;
}