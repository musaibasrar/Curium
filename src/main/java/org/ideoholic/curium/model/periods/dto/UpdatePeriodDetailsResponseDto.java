package org.ideoholic.curium.model.periods.dto;

import java.util.List;
import java.util.Map;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class UpdatePeriodDetailsResponseDto {
    private Periodmaster periodMaster;
    private List<Perioddetails> periodDetails;
    private Map<String,List<Perioddetails>> periodMap;
    private String periodMasterId;
    private List<Classsec> classsecList;
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private List<Subject> subjects;
}
