package org.ideoholic.curium.model.assessmentsubjectdetails.dto;

import lombok.Data;

@Data
public class AssessmentSubjectDto {
    private String subjectName;
    private String category;
    private String minRating;
    private String maxRating;
    private String assessmentName;
    private String[] subjectNameList;
    private String[] assessmentClassList;

}
