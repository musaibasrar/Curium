package org.ideoholic.curium.model.feescollection.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;

public class Studentotherfeesreport {
	private Parents parent;
	private List<Studentotherfeesstructure> studentFeesStructure;
	private int userid;

	public Studentotherfeesreport() {
	}


	public Studentotherfeesreport(Parents parents, List<Studentotherfeesstructure> studentFeesStructure) {
		this.parent = parents;
		this.studentFeesStructure = studentFeesStructure;
	}


	public Parents getParents() {
		return parent;
	}


	public void setParents(Parents parents) {
		this.parent = parents;
	}

	public List<Studentotherfeesstructure> getStudentFeesStructure() {
		return studentFeesStructure;
	}


	public void setStudentFeesStructure(List<Studentotherfeesstructure> studentFeesStructure) {
		this.studentFeesStructure = studentFeesStructure;
	}

	public int getUserid() {
			return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}


}