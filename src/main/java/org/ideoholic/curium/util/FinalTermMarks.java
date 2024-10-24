package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FinalTermMarks implements Serializable{
	
	String examName;
	Map<String,String> subMarks;
	float totalMarksObtained;
	float totalMarks;
	double percentage;
    String resultclass;
    Integer rank;
    
	public FinalTermMarks() {
	}

	public FinalTermMarks(String examName, Map<String,String> subMarks, double percentage, String resultclass, 
			float totalMarksObtained, Integer rank, float totalMarks) {
		this.examName = examName;
		this.subMarks = subMarks;
		this.percentage = percentage;
	    this.resultclass = resultclass;
	    this.totalMarksObtained = totalMarksObtained;
	    this.rank = rank;
	    this.totalMarks = totalMarks;
	}
	
	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Map<String, String> getSubMarks() {
		return subMarks;
	}

	public void setSubMarks(Map<String, String> subMarks) {
		this.subMarks = subMarks;
	}
	
	 public String getResultclass() {
	        return this.resultclass;
	    }

	    
	    public void setResultclass(String resultclass) {
	        this.resultclass = resultclass;
	    }

	    
	    public double getPercentage() {
	        return this.percentage;
	    }

	    
	    public void setPercentage(double percentage) {
	        this.percentage = percentage;
	    }

	    
	    public float getTotalMarksObtained() {
	        return this.totalMarksObtained;
	    }

	    
	    public void setTotalMarksObtained(float totalMarksObtained) {
	        this.totalMarksObtained = totalMarksObtained;
	    }

	    public Integer getRank() {
	        return this.rank;
	    }

	    
	    public void setRank(Integer rank) {
	        this.rank = rank;
	    }

		public float getTotalMarks() {
			return totalMarks;
		}

		public void setTotalMarks(float totalMarks) {
			this.totalMarks = totalMarks;
		}
	
}
