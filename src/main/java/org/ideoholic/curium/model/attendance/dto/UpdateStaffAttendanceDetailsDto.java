package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class UpdateStaffAttendanceDetailsDto {
    private String[] attendanceIds;
    private String[] studentAttendanceStatus;
    private String currentAcademicYear;
}
