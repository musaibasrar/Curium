package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.List;

public class AssessmentsDetails implements Serializable {

    String assessmentName;
    List<String> subjects;
    List<String> ratings;

    public AssessmentsDetails() {
    }

    public AssessmentsDetails(String assessmentName, List<String> subjects, List<String> ratings) {
        this.assessmentName = assessmentName;
        this.subjects = subjects;
        this.ratings = ratings;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }
}
