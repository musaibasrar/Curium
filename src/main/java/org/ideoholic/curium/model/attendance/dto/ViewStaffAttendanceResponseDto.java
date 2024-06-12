package org.ideoholic.curium.model.attendance.dto;

import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.employee.dto.Teacher;

import java.util.List;

@Data
@Builder
public class ViewStaffAttendanceResponseDto {
    private List<Staffdailyattendance> staffDailyAttendance;
    private List<Teacher> staffList;
    private String staffName;
    private int totalPresent;
    private int totalAbsent;
    private boolean success;
}
