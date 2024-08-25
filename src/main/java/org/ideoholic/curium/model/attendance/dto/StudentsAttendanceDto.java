package org.ideoholic.curium.model.attendance.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StudentsAttendanceDto {
    private String[] attendanceIds;
    private String[] studentAttendanceStatus;
    private Date dateofAttendance;
}
