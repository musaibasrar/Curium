package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class StudentAttendanceGraphDto {
    private String studentExternalIdGraph;
    private String fromDate;
    private String toDate;
    private String studentNameGraph;
    private String admNoGraph;
}
