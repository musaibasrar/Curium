package org.ideoholic.curium.model.assessmentsubjectdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.ideoholic.curium.model.std.dto.Classsec;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentSubjectsAssessmentsResponseDto {
    private List<AssessmentSubject> subjects;
    private List<AssessmentSubject> subjectNames;
    private List<AssessmentSubjectMaster> listSubjectNames;
    private List<HolisticAssessment> assessments;
    private List<Classsec> classsecList;
    private boolean success;

}
