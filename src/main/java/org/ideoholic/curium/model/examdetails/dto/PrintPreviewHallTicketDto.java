package org.ideoholic.curium.model.examdetails.dto;

import lombok.Data;

@Data
public class PrintPreviewHallTicketDto {
    private String[] examName;
    private String[] classes;
    private String[] subject;
    private String[] dateOfExam;
    private String[] startTime;
    private String[] endTime;
    private String classAndSec;
    private String admNo;
    private String studentName;
    private String academicYear;
    private String classStudying;
}
