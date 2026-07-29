package org.ideoholic.curium.model.assessmentdetails.dto;

import lombok.Data;

@Data
public class AddAssessmentScheduleDto {
    private String[] subject;
    private String[] date;
    private String[] startTime;
    private String[] endTime;
    private String[] classesSelected;
    private String academicyear;
    private String assessment;
    private String sectionSelected;
}
