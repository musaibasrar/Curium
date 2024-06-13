package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class StudentAttendanceDetailsDto {
    private String studentName;
    private String addClass;
    private String addSec;
    private String searchDate;
    private String dateOfAttendance;
}
