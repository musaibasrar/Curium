package org.ideoholic.curium.model.studentdiary.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studentdiary")
public class StudentDiary implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "sid")
	private Integer sid;

	@Column(name = "classsec", length = 45)
	private String classsec;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@Column(name = "subject", length = 100)
	private String subject;

	@Column(name = "message", length = 1000)
	private String message;

	@Temporal(TemporalType.DATE)
	@Column(name = "createddate")
	private Date createddate;

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "userid")
	private Integer userid;

}
