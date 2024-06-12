package org.ideoholic.curium.model.attendance.dto;

import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.student.dto.Student;

import java.util.List;

@Data
@Builder
public class ViewStudentAttendanceDetailsMarkResponseDto {
    private List<Student> studentListAttendance;
    private String attendanceClass;
    private String attendanceClassSearch;
    private boolean success;
}
