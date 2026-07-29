package org.ideoholic.curium.model.ratingdetails.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentReportDto {
    private List searchStudentList;
    private List assessmentSubjectList;
    private List assessmentList;
    private List studentList;
}
