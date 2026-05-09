package org.ideoholic.curium.util;

import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.marksdetails.dto.ExamSummary;
import org.ideoholic.curium.model.marksdetails.dto.SubjectSummary;
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
    Map<String, Map<String, String>> subjectExamMarks; // Subject -> (Exam -> Marks)
    List<ExamSummary> examSummaries;
    List<SubjectSummary> subjectSummaries;
    List<ExamsDetails> examsDetails;
    
    public MarksSheet() {
    }

    public MarksSheet(Parents parents, List<ExamsMarks> exammarks, List<FinalTermMarks> finaltermmarks,  List<ExamsMarks> otherexammarks, String overallresult, int totalDays, int totalPresent, int totalAbsent,Map<String, Map<String, String>> subjectExamMarks,List<ExamSummary> examSummaries,List<SubjectSummary> subjectSummaries, List<ExamsDetails> examsDetails) {
        this.parents = parents;
        this.exammarks = exammarks;
        this.finaltermmarks = finaltermmarks;
        this.otherexammarks = otherexammarks;
        this.overallresult = overallresult;
        this.totalDays = totalDays;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.subjectExamMarks = subjectExamMarks;
        this.examSummaries = examSummaries;
        this.subjectSummaries = subjectSummaries;
        this.examsDetails = examsDetails;
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
	
	public Map<String, Map<String, String>> getSubjectExamMarks() {
		return subjectExamMarks;
	}

	public void setSubjectExamMarks(Map<String, Map<String, String>> subjectExamMarks) {
		this.subjectExamMarks = subjectExamMarks;
	}

	public List<ExamSummary> getExamSummaries() {
		return examSummaries;
	}

	public void setExamSummaries(List<ExamSummary> examSummaries) {
		this.examSummaries = examSummaries;
	}

	public List<SubjectSummary> getSubjectSummaries() {
		return subjectSummaries;
	}

	public void setSubjectSummaries(List<SubjectSummary> subjectSummaries) {
		this.subjectSummaries = subjectSummaries;
	}

	public List<ExamsDetails> getExamsDetails() {
		return examsDetails;
	}

	public void setExamsDetails(List<ExamsDetails> examsDetails) {
		this.examsDetails = examsDetails;
	}
	
  }
