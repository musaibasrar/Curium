package org.ideoholic.curium.model.diary.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ideoholic.curium.model.enquiry.dto.Enquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diary")
public class Diary implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "classsec", length = 45)
	private String classsec;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@Column(name = "subject", length = 100)
	private String subject;

	@Column(name = "message", length = 1000)
	private String message;

	@Temporal(TemporalType.DATE)
	@Column(name = "startdate")
	private Date startdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "enddate")
	private Date enddate;

	@Temporal(TemporalType.DATE)
	@Column(name = "createddate")
	private Date createddate;

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "userid")
	private Integer userid;

}
