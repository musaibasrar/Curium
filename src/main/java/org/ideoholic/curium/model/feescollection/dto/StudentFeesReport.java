package org.ideoholic.curium.model.feescollection.dto;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;

public class StudentFeesReport {

	
	private Student student;
	private List<Studentfeesstructure> studentFeesStructure;
	
	public StudentFeesReport() {
	}


	public StudentFeesReport(Student student, List<Studentfeesstructure> studentFeesStructure) {
		this.student = student;
		this.studentFeesStructure = studentFeesStructure;
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

	
}
