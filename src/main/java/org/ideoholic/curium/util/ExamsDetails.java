package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.List;

public class ExamsDetails implements Serializable{
	
	String examName;
	List<String> subjects;
	List<Float> marks;
	
	public ExamsDetails() {
	}

	public ExamsDetails(String examName, List<String> subjects, List<Float> marks) {
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
	public List<Float> getMarks() {
		return marks;
	}
	public void setMarks(List<Float> marks) {
		this.marks = marks;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	
}
