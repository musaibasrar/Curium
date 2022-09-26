package com.util;

import java.util.List;

import com.model.student.dto.Student;

public class Result  implements java.io.Serializable,Comparable<Result> {
    
    Student student;
    String fathername;
    List<Integer> marksList;
    List<String> subjectList;
    List<String> referenceBooksList;
    double percentage;
    String resultclass;
    Integer rank;
    
    public Result() {
    }

    public Result(Student student, List<Integer> marksList, double percentage, String resultclass, List<String> subjectList, List<String> referenceBooksList, Integer rank, String fathername) {
        this.student = student;
        this.subjectList = subjectList;
        this.marksList = marksList;
        this.percentage = percentage;
        this.resultclass = resultclass;
        this.referenceBooksList = referenceBooksList;
        this.rank = rank;
        this.fathername = fathername;
    }

    
    public Student getStudent() {
        return this.student;
    }

    
    public void setStudent(Student student) {
        this.student = student;
    }

    public String getResultclass() {
        return this.resultclass;
    }

    
    public void setResultclass(String resultclass) {
        this.resultclass = resultclass;
    }

    
    public List<Integer> getMarksList() {
        return this.marksList;
    }

    
    public void setMarksList(List<Integer> marksList) {
        this.marksList = marksList;
    }

    
    public List<String> getSubjectList() {
        return this.subjectList;
    }

    
    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    
    public double getPercentage() {
        return this.percentage;
    }

    
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    
    public List<String> getReferenceBooksList() {
        return this.referenceBooksList;
    }

    
    public void setReferenceBooksList(List<String> referenceBooksList) {
        this.referenceBooksList = referenceBooksList;
    }

    @Override
    public int compareTo(Result result) {
        double percentage = ((Result) result).getPercentage();
        return Double.compare(percentage, this.percentage);
    }

    
    public Integer getRank() {
        return this.rank;
    }

    
    public void setRank(Integer rank) {
        this.rank = rank;
    }

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
  }
