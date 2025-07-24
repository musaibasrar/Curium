package org.ideoholic.curium.model.employee.dto;

import lombok.Data;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;

import java.util.List;

@Data
public class ViewAllRelationsResponseDto {
private List<Department> listDepartment;
private List<Position> listPosition;
private List<Classsec> listClasssec;
private List<Subjectmaster> listSubjectMaster;
private List<String> classList;
private List<String> sectionList;
}
