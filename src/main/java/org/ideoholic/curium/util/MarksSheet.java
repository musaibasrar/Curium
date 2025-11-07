package org.ideoholic.curium.util;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

public class MarksSheet  implements java.io.Serializable {
    
    Parents parents;
    List<ExamsMarks> exammarks;
    List<FinalTermMarks> finaltermmarks;
    List<ExamsMarks> otherexammarks;
    String overallresult;
    int totalDays;
    int totalPresent;
    int totalAbsent;    
    
    public MarksSheet() {
    }

    public MarksSheet(Parents parents, List<ExamsMarks> exammarks, List<FinalTermMarks> finaltermmarks,  List<ExamsMarks> otherexammarks, String overallresult, int totalDays, int totalPresent, int totalAbsent) {
        this.parents = parents;
        this.exammarks = exammarks;
        this.finaltermmarks = finaltermmarks;
        this.otherexammarks = otherexammarks;
        this.overallresult = overallresult;
        this.totalDays = totalDays;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
    }


    
    public Parents getParents() {
        return this.parents;
    }

    
    public void setParents(Parents parents) {
        this.parents = parents;
    }

	public List<ExamsMarks> getExammarks() {
		return exammarks;
	}

	public void setExammarks(List<ExamsMarks> exammarks) {
		this.exammarks = exammarks;
	}

	public List<FinalTermMarks> getFinaltermmarks() {
		return finaltermmarks;
	}

	public void setFinaltermmarks(List<FinalTermMarks> finaltermmarks) {
		this.finaltermmarks = finaltermmarks;
	}

	public List<ExamsMarks> getOtherexammarks() {
		return otherexammarks;
	}

	public void setOtherexammarks(List<ExamsMarks> otherexammarks) {
		this.otherexammarks = otherexammarks;
	}

	public String getOverallresult() {
		return overallresult;
	}

	public void setOverallresult(String overallresult) {
		this.overallresult = overallresult;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getTotalPresent() {
		return totalPresent;
	}

	public void setTotalPresent(int totalPresent) {
		this.totalPresent = totalPresent;
	}

	public int getTotalAbsent() {
		return totalAbsent;
	}

	public void setTotalAbsent(int totalAbsent) {
		this.totalAbsent = totalAbsent;
	}
	
  }
