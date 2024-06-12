package org.ideoholic.curium.model.attendance.dto;

import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.employee.dto.Teacher;

import java.util.List;

@Data
@Builder
public class SearchStaffAttendanceDetailsResponseDto {
    private List<Teacher> staffListAttendance;
    private List<Staffdailyattendance> staffDailyAttendanceDate;
    private List<Teacher> staffList;
    private String searchedDate;
    private boolean success;
}
