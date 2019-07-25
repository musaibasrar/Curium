package com.util;

import java.util.List;

import com.model.parents.dto.Parents;

public class MarksSheet  implements java.io.Serializable,Comparable<MarksSheet> {
    
    Parents parents;
    List<Integer> marksList;
    List<String> subjectList;
    List<String> referenceBooksList;
    int totalMarksObtained;
    double percentage;
    String resultclass;
    Integer rank;
    int totalMarks;
    
    public MarksSheet() {
    }

    public MarksSheet(Parents parents, List<Integer> marksList, double percentage, String resultclass, List<String> subjectList, 
            List<String> referenceBooksList, int totalMarksObtained, Integer rank, int totalMarks) {
        this.parents = parents;
        this.subjectList = subjectList;
        this.marksList = marksList;
        this.percentage = percentage;
        this.resultclass = resultclass;
        this.referenceBooksList = referenceBooksList;
        this.totalMarksObtained = totalMarksObtained;
        this.rank = rank;
        this.totalMarks = totalMarks;
    }


    
    public Parents getParents() {
        return this.parents;
    }

    
    public void setParents(Parents parents) {
        this.parents = parents;
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

    
    public int getTotalMarksObtained() {
        return this.totalMarksObtained;
    }

    
    public void setTotalMarksObtained(int totalMarksObtained) {
        this.totalMarksObtained = totalMarksObtained;
    }

    @Override
    public int compareTo(MarksSheet result) {
        double percentage = ((MarksSheet) result).getPercentage();
        return Double.compare(percentage, this.percentage);
    }

    
    public Integer getRank() {
        return this.rank;
    }

    
    public void setRank(Integer rank) {
        this.rank = rank;
    }

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
  }
