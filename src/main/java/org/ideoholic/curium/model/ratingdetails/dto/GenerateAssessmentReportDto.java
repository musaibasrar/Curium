package org.ideoholic.curium.model.ratingdetails.dto;

import lombok.Data;

@Data
public class GenerateAssessmentReportDto {
    private String[] studentIds;
    private String assessmentClass;
    private String[] ratingIds;
    private String studentUID;
    private String assessmentDetailsID;
    private String[] assessmentIds;
    private String totalDaysPresent;
    private String assessmentName;
    private String noofpresentday;
    private String endDate;
    private String startDate;
}
