package org.ideoholic.curium.model.ratingdetails.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectSummary {
    private String subjectName;
    @Builder.Default
    private List<AssessmentSummary> assessmentRatings = new ArrayList<>();
    private double totalPercentage;
    private String overallGrade;

    public void addAssessmentRating(String assessmentName, String grade) {
        this.assessmentRatings.add(AssessmentSummary.builder()
                .assessmentName(assessmentName)
                .grade(grade)
                .build());
    }
}
