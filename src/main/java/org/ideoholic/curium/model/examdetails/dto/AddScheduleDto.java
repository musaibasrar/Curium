package org.ideoholic.curium.model.examdetails.dto;

import lombok.Data;

@Data
public class AddScheduleDto {
    private String[] subject;
    private String[] date;
    private String[] startTime;
    private String[] endTime;
    private String[] classesSelected;
    private String academicyear;
    private String exam;
}
