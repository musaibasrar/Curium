package org.ideoholic.curium.model.student.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 24 Jul, 2015 12:41:34 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ideoholic.curium.model.feescategory.dto.Feescategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Studentfeesstructure generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee_studentfeesstructure")
public class Studentfeesstructure implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sfsid", unique = true, nullable = false)
	private Integer sfsid;

	@Column(name = "sid")
	private Integer sid;

	@Column(name = "idfeescategory")
	private Integer idfeescategory;

	@Column(name = "feesamount", precision = 10, scale = 0)
	private Long feesamount;

	@Column(name = "feespaid", precision = 10, scale = 0)
	private Long feespaid;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "concession")
	private Integer concession;

	@Column(name = "waiveoff", precision = 10, scale = 0)
	private Long waiveoff;

	@Column(name = "totalinstallment")
	private Integer totalinstallment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idfeescategory")
	private Feescategory feescategory;

	@Column(name = "userid")
	private int userid;

}
