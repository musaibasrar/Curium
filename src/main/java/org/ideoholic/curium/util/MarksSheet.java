package org.ideoholic.curium.util;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

public class MarksSheet  implements java.io.Serializable {
    
    Parents parents;
    List<ExamsMarks> exammarks;
    List<ExamsMarks> finaltermmarks;
    
    public MarksSheet() {
    }

    public MarksSheet(Parents parents, List<ExamsMarks> exammarks, List<ExamsMarks> finaltermmarks) {
        this.parents = parents;
        this.exammarks = exammarks;
        this.finaltermmarks = finaltermmarks;
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

	public List<ExamsMarks> getFinaltermmarks() {
		return finaltermmarks;
	}

	public void setFinaltermmarks(List<ExamsMarks> finaltermmarks) {
		this.finaltermmarks = finaltermmarks;
	}
  }
