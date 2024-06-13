package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class StudentAttendanceMonthlyDto {
    private String studentExternalId;
    private String fromDate;
    private String toDate;
    private String studentName;
    private String admNo;
}
