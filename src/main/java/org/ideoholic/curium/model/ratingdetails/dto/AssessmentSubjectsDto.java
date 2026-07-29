package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentSubjectsDto {
    private List<AssessmentSubject> assessmentSubjects;
    private List<HolisticAssessment> assessments;
    private List classsecList;
    private boolean success;
}
