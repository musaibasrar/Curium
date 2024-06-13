package org.ideoholic.curium.model.attendance.dto;

import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.student.dto.Student;

import java.util.List;

@Data
@Builder
public class StudentAttendanceMonthlyResponseDto {
    private boolean success;
    private List<Studentdailyattendance> studentDailyAttendance;
    private String studentName;
    private String admNo;
    private int totalPresent;
    private int totalAbsent;
    private List<Student> studentList;
}
