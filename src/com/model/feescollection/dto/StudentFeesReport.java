package com.model.feescollection.dto;

import java.util.List;

import com.model.parents.dto.Parents;
import com.model.student.dto.Studentfeesstructure;

public class StudentFeesReport {

	
	private Parents parents;
	private List<Studentfeesstructure> studentFeesStructure;
	private int userid;
	
	public StudentFeesReport() {
	}


	public StudentFeesReport(Parents parents, List<Studentfeesstructure> studentFeesStructure) {
		this.parents= parents;
		this.studentFeesStructure = studentFeesStructure;
	}
	

	public Parents getParents() {
		return parents;
	}


	public void setParents(Parents parents) {
		this.parents = parents;
	}

	public List<Studentfeesstructure> getStudentFeesStructure() {
		return studentFeesStructure;
	}


	public void setStudentFeesStructure(List<Studentfeesstructure> studentFeesStructure) {
		this.studentFeesStructure = studentFeesStructure;
	}
	
	public int getUserid() {
			return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	
}
