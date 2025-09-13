package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SubjectAverage implements Serializable{
	
	String examName;
	List<String> classsec;
	List<Float> averageMarks;
	
    
	public SubjectAverage() {
	}

	

	public SubjectAverage(String examName, List<String> classsec, List<Float>averageMarks) {
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



	public List<Float> getAverageMarks() {
		return averageMarks;
	}



	public void setAverageMarks(List<Float> averageMarks) {
		this.averageMarks = averageMarks;
	}

	

}
