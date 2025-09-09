package org.ideoholic.curium.model.documents.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studycertificate")
public class StudyCertificate implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 100)
	private String name; 
	
	@Column(name = "fathername", length = 100)
	private String fatherName;

	@Column(name = "classstudying",length = 20)
	private String classStudying;
	
	@Column(name = "reason", length = 100)
	private String reason;

	@Column(name = "noofissues")
	private Integer noofissues;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofissues", length = 10)
	private Date dateofissues;

	@Column(name = "academicyear", length = 100)
	private String academicYear;
	
	@Column(name = "uid", length = 100)
	private String uid;

	@Column(name = "sid")
	private Integer sid;

	@Column(name = "branchid")
	private int branchId;

	@Column(name = "userid")
	private int userid;

}
