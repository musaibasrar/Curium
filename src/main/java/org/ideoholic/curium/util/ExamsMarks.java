package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ExamsMarks implements Serializable,Comparable<ExamsMarks>{
	
	String examName;
	Map<String,String> subMarks;
	int totalMarksObtained;
	int totalMarks;
	double percentage;
    String resultclass;
    Integer rank;
    
	public ExamsMarks() {
	}

	public ExamsMarks(String examName, Map<String,String> subMarks, double percentage, String resultclass, 
            int totalMarksObtained, Integer rank, int totalMarks) {
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

	    
	    public int getTotalMarksObtained() {
	        return this.totalMarksObtained;
	    }

	    
	    public void setTotalMarksObtained(int totalMarksObtained) {
	        this.totalMarksObtained = totalMarksObtained;
	    }

	    @Override
	    public int compareTo(ExamsMarks result) {
	        double percentage = ((ExamsMarks) result).getPercentage();
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
