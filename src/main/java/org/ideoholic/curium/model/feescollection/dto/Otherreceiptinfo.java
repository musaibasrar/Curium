package org.ideoholic.curium.model.feescollection.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ideoholic.curium.model.student.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otherfee_receiptinfo")
public class Otherreceiptinfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receiptnumber", unique = true, nullable = false)
	private Integer receiptnumber;

	@ManyToOne(optional = false)
	@JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false)
	private Student student;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	private Date date;

	@Column(name = "totalamount", precision = 10, scale = 0)
	private Long totalamount;

	@Column(name = "academicyear", length = 15)
	private String academicyear;

	@Column(name = "cancelreceipt")
	private int cancelreceipt;

	@Column(name = "branchreceiptnumber", length = 20)
	private String branchreceiptnumber;

	@Column(name = "paymenttype", length = 100)
	private String paymenttype;

	@Column(name = "classsec", length = 20)
	private String classsec;

	@Column(name = "receiptvoucher")
	private Integer receiptvoucher;

	@Column(name = "journalvoucher")
	private Integer journalvoucher;

	@Column(name = "fine")
	private Long fine;

	@Column(name = "misc")
	private Long misc;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptInfo")
	private Set<Otherfeescollection> feesCollectionRecords = new HashSet<Otherfeescollection>(0);

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "userid")
	private Integer userid;
	
	public int fetchSid() {
		if (student != null) {
			return student.getSid();
		}
		return 0;
	}
}