package org.ideoholic.curium.model.assessmentdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentScheduleResponseDto {
    private List<HolisticAssessment> assessments;
    private List<AssessmentSubject> list;
    private List<AssessmentSubjectMaster> listSubjectName;
    private boolean success;
    private String selectedclass;
    private String selectedassessment;
    private String selectedstudentname;
    private String selectedclassandsec;
    private String selectedadmissionno;
    private List<HolisticAssessmentSchedule> assessmentschedules;

    private String message;
    private Map resultMap;
    private Integer resultValue;
    private List resultList;

    private String currentacademicyear;
}
