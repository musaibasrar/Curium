package com.model.importfile.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "import_file")
public class ImportFileDTO implements java.io.Serializable{
	
	private String studentname;
	private String studentclass;
	
	public ImportFileDTO() {
	}

	public ImportFileDTO(String studentname, String studentclass) {
		this.studentname = studentname;
		this.studentclass = studentclass;
	
	}
	
	@Column(name = "studentname")
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	@Column(name = "studentclass")
	public String getStudentclass() {
		return studentclass;
	}
	public void setStudentclass(String studentclass) {
		this.studentclass = studentclass;
	}
	
}