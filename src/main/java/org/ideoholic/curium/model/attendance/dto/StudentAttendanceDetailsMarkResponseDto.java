package org.ideoholic.curium.model.attendance.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentAttendanceDetailsMarkResponseDto {
    private List<Parents> studentListAttendance;
    private String attendanceClass;
    private String attendanceClassSearch;
    private boolean success;
}
