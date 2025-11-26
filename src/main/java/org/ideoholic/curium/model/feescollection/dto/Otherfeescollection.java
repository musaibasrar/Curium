package org.ideoholic.curium.model.feescollection.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otherfee_feescollection")
public class Otherfeescollection implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feecollectionid", unique = true, nullable = false)
	private Integer feecollectionid;

	@Column(name = "amountpaid")
	private Long amountpaid;

	@Column(name = "fine")
	private Long fine;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Column(name = "academicyear", length = 45)
	private String academicyear;

	@JoinColumn(name = "receiptnumber", referencedColumnName = "receiptnumber", nullable = false)
	@ManyToOne(optional = false)
	private Otherreceiptinfo receiptInfo;

	@JoinColumn(name = "sfsid", referencedColumnName = "sfsid", nullable = false)
	@ManyToOne(optional = false)
	private Studentotherfeesstructure otherFeesStructure;
	
	@JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false)
	@ManyToOne(optional = false)
	private Student student;

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "userid")
	private Integer userid;
	
	public Integer fetchSfsid() {
		if (otherFeesStructure != null) {
			return otherFeesStructure.fetchSfsid();
		}
		return 0;
	}
}