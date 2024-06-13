package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class AttendanceDetailsDto {
    private String[] attendanceIds;
    private String[] studentAttendanceStatus;
}
