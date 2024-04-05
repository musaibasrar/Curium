package org.ideoholic.curium.util;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

public class MarksSheet  implements java.io.Serializable {
    
    Parents parents;
    List<ExamsMarks> exammarks;
    List<FinalTermMarks> finaltermmarks;
    List<ExamsMarks> otherexammarks;
    String overallresult;
    
    
    public MarksSheet() {
    }

    public MarksSheet(Parents parents, List<ExamsMarks> exammarks, List<FinalTermMarks> finaltermmarks,  List<ExamsMarks> otherexammarks, String overallresult) {
        this.parents = parents;
        this.exammarks = exammarks;
        this.finaltermmarks = finaltermmarks;
        this.otherexammarks = otherexammarks;
        this.overallresult = overallresult;
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
	
  }
