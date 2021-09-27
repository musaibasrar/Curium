package com.util;

import java.io.Serializable;
import java.util.List;

public class ExamsDetails implements Serializable{
	
	String examName;
	List<String> subjects;
	List<Integer> marks;
	
	public ExamsDetails() {
	}

	public ExamsDetails(String examName, List<String> subjects, List<Integer> marks) {
		this.examName = examName;
		this.subjects = subjects;
		this.marks = marks;
	}
	
	public List<String> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
	public List<Integer> getMarks() {
		return marks;
	}
	public void setMarks(List<Integer> marks) {
		this.marks = marks;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	
}
