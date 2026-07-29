package org.ideoholic.curium.util;

import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.ratingdetails.dto.AssessmentSummary;
import org.ideoholic.curium.model.ratingdetails.dto.SubjectSummary;

/**
 * AssessmentSheet - Utility class for Holistic Development Assessment reports
 * Duplicated from MarksSheet for independent assessment module
 * Handles grade-based assessment data instead of numeric marks
 */
public class AssessmentSheet implements java.io.Serializable {
    
    Parents parents;
    List<AssessmentRatings> assessmentratings;
    List<FinalTermRatings> finaltermratings;
    List<AssessmentRatings> otherassessmentratings;
    String overallresult;
    int totalDays;
    int totalPresent;
    int totalAbsent;
    Map<String, Map<String, String>> subjectAssessmentRatings; // Subject -> (Assessment -> Grade)
    List<AssessmentSummary> assessmentSummaries;
    List<SubjectSummary> subjectSummaries;
    List<AssessmentsDetails> assessmentsDetails;
    
    public AssessmentSheet() {
    }

    public AssessmentSheet(Parents parents, List<AssessmentRatings> assessmentratings, 
            List<FinalTermRatings> finaltermratings, List<AssessmentRatings> otherassessmentratings, 
            String overallresult, int totalDays, int totalPresent, int totalAbsent,
            Map<String, Map<String, String>> subjectAssessmentRatings,
            List<AssessmentSummary> assessmentSummaries, List<SubjectSummary> subjectSummaries, 
            List<AssessmentsDetails> assessmentsDetails) {
        this.parents = parents;
        this.assessmentratings = assessmentratings;
        this.finaltermratings = finaltermratings;
        this.otherassessmentratings = otherassessmentratings;
        this.overallresult = overallresult;
        this.totalDays = totalDays;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.subjectAssessmentRatings = subjectAssessmentRatings;
        this.assessmentSummaries = assessmentSummaries;
        this.subjectSummaries = subjectSummaries;
        this.assessmentsDetails = assessmentsDetails;
    }

    public Parents getParents() {
        return this.parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public List<AssessmentRatings> getAssessmentratings() {
        return assessmentratings;
    }

    public void setAssessmentratings(List<AssessmentRatings> assessmentratings) {
        this.assessmentratings = assessmentratings;
    }

    public List<FinalTermRatings> getFinaltermratings() {
        return finaltermratings;
    }

    public void setFinaltermratings(List<FinalTermRatings> finaltermratings) {
        this.finaltermratings = finaltermratings;
    }

    public List<AssessmentRatings> getOtherassessmentratings() {
        return otherassessmentratings;
    }

    public void setOtherassessmentratings(List<AssessmentRatings> otherassessmentratings) {
        this.otherassessmentratings = otherassessmentratings;
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

    public Map<String, Map<String, String>> getSubjectAssessmentRatings() {
        return subjectAssessmentRatings;
    }

    public void setSubjectAssessmentRatings(Map<String, Map<String, String>> subjectAssessmentRatings) {
        this.subjectAssessmentRatings = subjectAssessmentRatings;
    }

    public List<AssessmentSummary> getAssessmentSummaries() {
        return assessmentSummaries;
    }

    public void setAssessmentSummaries(List<AssessmentSummary> assessmentSummaries) {
        this.assessmentSummaries = assessmentSummaries;
    }

    public List<SubjectSummary> getSubjectSummaries() {
        return subjectSummaries;
    }

    public void setSubjectSummaries(List<SubjectSummary> subjectSummaries) {
        this.subjectSummaries = subjectSummaries;
    }

    public List<AssessmentsDetails> getAssessmentsDetails() {
        return assessmentsDetails;
    }

    public void setAssessmentsDetails(List<AssessmentsDetails> assessmentsDetails) {
        this.assessmentsDetails = assessmentsDetails;
    }
}