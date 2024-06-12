package org.ideoholic.curium.model.attendance.dto;

import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.student.dto.Student;

import java.util.List;

@Data
@Builder
public class StudentAttendanceGraphResponseDto {
    private List<String> xAxis;
    private List<Integer> yAxis;
    private List<Student> studentList;
    private String studentNameGraph;
    private String admNoGraph;
    private boolean success;
}
