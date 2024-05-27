package org.ideoholic.curium.model.feescollection.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;

public class StudentFeesReport {

	
	private Parents parent;
	private List<Studentfeesstructure> studentFeesStructure;
	private Long dueAmount;
	private int userid;
	
	public StudentFeesReport() {
	}


	public StudentFeesReport(Parents parents, List<Studentfeesstructure> studentFeesStructure, Long dueAmount) {
		this.parent = parents;
		this.studentFeesStructure = studentFeesStructure;
		this.dueAmount = dueAmount;
	}
	

	public Parents getParents() {
		return parent;
	}


	public void setParents(Parents parents) {
		this.parent = parents;
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
	
	public Long getDueAmount() {
		return dueAmount;
	}


	public void setDueAmount(Long dueAmount) {
		this.dueAmount = dueAmount;
	}

	
}
