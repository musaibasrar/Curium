package com.model.feescollection.dto;

import java.util.List;

import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;

public class StudentFeesReport {

	
	private Student student;
	private List<Studentfeesstructure> studentFeesStructure;
	private int userid;
	
	public StudentFeesReport() {
	}


	public StudentFeesReport(Student student, List<Studentfeesstructure> studentFeesStructure, int userid) {
		this.student = student;
		this.studentFeesStructure = studentFeesStructure;
		this.userid = userid;
	}
	

	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
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
