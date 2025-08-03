package org.ideoholic.curium.model.marksdetails.dto;

import lombok.Data;

@Data
public class MarksUpdateDto {
    private String[] studentIds;
    private String[] studentsMarks;
    private String[] marksId;
    private String exam;
    private String subject;
    private String classSearch;
    private String academicYear;
    private String[] studentsMarksA1;
    private String[] studentsMarksA2;
    private String[] studentsMarksA3;
    private String[] studentsMarksA4;
}
