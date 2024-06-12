package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class ViewStaffAttendanceDto {
    private String staffExternalId;
    private String fromDate;
    private String toDate;
    private String nameOfStaff;
}
