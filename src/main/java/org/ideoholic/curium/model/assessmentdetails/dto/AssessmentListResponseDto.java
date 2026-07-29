package org.ideoholic.curium.model.assessmentdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentListResponseDto {
    private List<HolisticAssessment> assessments;
    private boolean success;
}
