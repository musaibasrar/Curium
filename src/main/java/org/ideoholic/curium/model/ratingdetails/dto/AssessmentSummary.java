package org.ideoholic.curium.model.ratingdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentSummary {
    private String assessmentName;
    private int assessmentId;
    private double percentage;
    private String grade;
    private int rank;
}
