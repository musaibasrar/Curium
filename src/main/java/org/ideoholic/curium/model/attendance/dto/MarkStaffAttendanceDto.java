package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class MarkStaffAttendanceDto {
    private String[] attendanceIds;
    private String[] staffAttendanceStatus;
    private String[] inTime;
    private String[] outTime;
    private Integer branchId;
    private String currentAcademicYear;
}
