package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.Map;

public class AssessmentRatings implements Serializable, Comparable<AssessmentRatings> {

    String assessmentName;
    Map<String, String> subjectRatings;
    double percentage;
    String resultclass;
    Integer rank;

    public AssessmentRatings() {
    }

    public AssessmentRatings(String assessmentName, Map<String, String> subjectRatings, double percentage,
            String resultclass, Integer rank) {
        this.assessmentName = assessmentName;
        this.subjectRatings = subjectRatings;
        this.percentage = percentage;
        this.resultclass = resultclass;
        this.rank = rank;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public Map<String, String> getSubjectRatings() {
        return subjectRatings;
    }

    public void setSubjectRatings(Map<String, String> subjectRatings) {
        this.subjectRatings = subjectRatings;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getResultclass() {
        return resultclass;
    }

    public void setResultclass(String resultclass) {
        this.resultclass = resultclass;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(AssessmentRatings other) {
        return Double.compare(other.percentage, this.percentage);
    }
}
