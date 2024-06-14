package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class StudentsAttendanceDto {
    private String[] attendanceIds;
    private String[] studentAttendanceStatus;
}
