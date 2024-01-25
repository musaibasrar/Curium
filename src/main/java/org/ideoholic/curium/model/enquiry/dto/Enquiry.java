package org.ideoholic.curium.model.enquiry.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enquiry")
public class Enquiry implements java.io.Serializable{
	private int id;
	private String name;
	private String fathername;
	private String mothername;
	private String admissionclass;
	private Date dateofbirth;
	private String address;
	private String mobileno;
	private String siblingstudy;
	private String lastschool;
	public Enquiry() {
	}
	public Enquiry(int id, String name, String fathername, String mothername, String admissionclass, Date dateofbirth,
			String address, String mobileno, String siblingstudy, String lastschool) {
		this.id = id;
		this.name = name;
		this.fathername = fathername;
		this.mothername = mothername;
		this.admissionclass = admissionclass;
		this.dateofbirth = dateofbirth;
		this.address = address;
		this.mobileno = mobileno;
		this.siblingstudy = siblingstudy;
		this.lastschool = lastschool;
	}
	
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "fathername", length = 45)
	public String getFathername() {
		return fathername;
	}
	
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	@Column(name = "mothername", length = 45)
	public String getMothername() {
		return mothername;
	}
	
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	
	@Column(name = "admissionclass", length = 45)
	public String getAdmissionclass() {
		return admissionclass;
	}
	
	public void setAdmissionclass(String admissionclass) {
		this.admissionclass = admissionclass;
	}
	
	@Column(name = "dateofbirth", length = 45)
	public Date getDateofbirth() {
		return dateofbirth;
	}
	
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	@Column(name = "address", length = 45)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mobileno", length = 45)
	public String getMobileno() {
		return mobileno;
	}
	
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	@Column(name = "siblingstudy", length = 45)
	public String getSiblingstudy() {
		return siblingstudy;
	}
	
	public void setSiblingstudy(String siblingstudy) {
		this.siblingstudy = siblingstudy;
	}
	
	@Column(name = "lastschool", length = 45)
	public String getLastschool() {
		return lastschool;
	}
	
	public void setLastschool(String lastschool) {
		this.lastschool = lastschool;
	}
    
}
