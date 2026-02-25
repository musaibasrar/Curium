package org.ideoholic.curium.model.periods.dto;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TimeTableResponseDto {
    private String  currentYear;
    private List<Periodmaster> periodMaster;
    private List<Subject> subjects;
    private List<Classsec> classSecs;
    private List<Teacher> employeeList;
    private List<Subjectmaster> subjectMasters;

    private boolean success;
}