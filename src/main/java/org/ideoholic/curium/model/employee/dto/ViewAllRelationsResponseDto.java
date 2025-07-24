package org.ideoholic.curium.model.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ViewAllRelationsResponseDto {
private List<Department> listDepartment;
private List<Position> listPosition;
private List<Classsec> listClasssec;
private List<Subjectmaster> listSubjectMaster;
private List<String> classList;
private List<String> sectionList;
}
