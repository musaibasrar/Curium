package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class StaffAttendanceMasterDto {
    private String[] staffId;
    private String[] weeklyOff;
    private String[] holidays;
    private String inTime;
    private String outTime;
}
