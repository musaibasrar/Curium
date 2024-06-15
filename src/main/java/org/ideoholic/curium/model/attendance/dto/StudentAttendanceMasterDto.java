package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class StudentAttendanceMasterDto {
    private String[] weeklyOff;
    private String[] holidays;
    private String inTime;
}
