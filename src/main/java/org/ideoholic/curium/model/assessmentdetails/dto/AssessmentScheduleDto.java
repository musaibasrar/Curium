package org.ideoholic.curium.model.assessmentdetails.dto;

import lombok.Data;

@Data
public class AssessmentScheduleDto {
    private String academicYear;
    private String classH;
    private String classAdmno;
    private String studentName;
    private String assessment;
    // Legacy fields kept for backward compatibility.
    private String assessmentName;
    private String classDetails;
    private String section;
}
