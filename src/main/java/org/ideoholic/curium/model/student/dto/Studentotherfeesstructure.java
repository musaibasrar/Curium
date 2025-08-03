package org.ideoholic.curium.model.student.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otherfee_studentfeesstructure")
public class Studentotherfeesstructure implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sfsid", unique = true, nullable = false)
	private Integer sfsid;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "sid", referencedColumnName = "sid", nullable = false)
    private Student student;
	
	@Column(name = "feesamount", precision = 10, scale = 0)
	private Long feesamount;
	
	@Column(name = "feespaid", precision = 10, scale = 0)
	private Long feespaid;
	
	@Column(name = "academicyear", length = 45)
	private String academicyear;
	
	@Column(name = "concession")
	private Integer concession;
	
	@Column(name = "waiveoff", precision = 10, scale = 0)
	private Long waiveoff;
	
	@Column(name = "totalinstallment")
	private Integer totalinstallment;

	@Column(name = "concessionnotes")
	private String concessionnotes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idfeescategory", referencedColumnName = "idfeescategory")
	private OtherFeecategory otherfeescategory;
	
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
	
	public Integer getSfsid() {
	    return sfsid;
	}

}
