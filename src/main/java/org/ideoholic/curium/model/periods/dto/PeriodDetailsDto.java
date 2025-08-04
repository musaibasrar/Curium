package org.ideoholic.curium.model.periods.dto;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class PeriodDetailsDto {
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private List<Subject> subjects;
    private List<Classsec> classSecs;

    private boolean success;
}