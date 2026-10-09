package org.ideoholic.curium.model.assessmentsubjectdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentSubjectsResponseDto {
    private List<AssessmentSubject> list;
    private List<AssessmentSubjectMaster> listSubjectNames;
    private boolean success;
}
