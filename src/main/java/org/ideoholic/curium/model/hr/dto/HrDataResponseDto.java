package org.ideoholic.curium.model.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.position.dto.Position;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class HrDataResponseDto {
    private List<Teacher> employeeList;

    private List<Department> listDepartment;
    private List<Position> listPosition;

    private List<Payhead> payHeadList;
    private boolean success;


    private List<Leavetypemaster> leavetypemaster;

    private List<Teacher> employeeListProcessSalary;

}
