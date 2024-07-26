package org.ideoholic.curium.model.examdetails.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamIdsDto {
    private String[] examIds;
    private String academicYear;
    private String classH;
    private String classAdmno;
    private String studentName;
    private String exam;

}
