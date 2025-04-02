package org.ideoholic.curium.model.enquiry.dto;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enquiry")
public class Enquiry implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "fathername", length = 100)
    private String fathername;
    
    @Column(name = "mothername", length = 100)
    private String mothername;
    
    @Column(name = "admissionclass", length = 45)
    private String admissionclass;
    
    @Column(name = "address", length = 200)
    private String address;
    
    @Column(name = "mobileno", length = 45)
    private String mobileno;
    
    @Column(name = "siblings", length = 300)
    private String siblingstudy;
    
    @Column(name = "status", length = 45)
    private String status;
    
    @Column(name = "academicYear", length = 45)
    private String academicYear;

	@Temporal(TemporalType.DATE)
	@Column(name = "created")
	private Date createddate;

	@Column(name = "branchid")
	private Integer branchId;

	@Column(name = "userid")
	private Integer userId;
	
}
