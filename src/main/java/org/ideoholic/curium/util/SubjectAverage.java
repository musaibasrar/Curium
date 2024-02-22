package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SubjectAverage implements Serializable{
	
	String examName;
	List<String> classsec;
	List<Integer> averageMarks;
	
    
	public SubjectAverage() {
	}

	

	public SubjectAverage(String examName, List<String> classsec, List<Integer>averageMarks) {
		this.examName = examName;
		this.averageMarks = averageMarks;
		this.classsec = classsec;
	}



	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public List<String> getClasssec() {
		return classsec;
	}



	public void setClasssec(List<String> classsec) {
		this.classsec = classsec;
	}



	public List<Integer> getAverageMarks() {
		return averageMarks;
	}



	public void setAverageMarks(List<Integer> averageMarks) {
		this.averageMarks = averageMarks;
	}

	

}
