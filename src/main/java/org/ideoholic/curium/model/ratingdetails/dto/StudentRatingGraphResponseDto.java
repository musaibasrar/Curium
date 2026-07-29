package org.ideoholic.curium.model.ratingdetails.dto;

import java.util.List;

import org.ideoholic.curium.util.AssessmentsDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRatingGraphResponseDto {
    private List<AssessmentsDetails> assessmentDetailsGraph;
    private int assessmentDetailsGraphSize;
    private String searchStudent;
    @Builder.Default
    private boolean success = false;
}
