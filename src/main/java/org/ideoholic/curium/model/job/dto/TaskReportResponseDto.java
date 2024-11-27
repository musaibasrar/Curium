package org.ideoholic.curium.model.job.dto;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TaskReportResponseDto {
	
	private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private boolean success=false;
    private List<Student> studentList;
	private List<Parents> parentDetails;


	

	public void copyStudentListResponseDto(StudentListResponseDto studentListResponseDto) {
		studentList = studentListResponseDto.getStudentList();
		parentDetails = studentListResponseDto.getParentDetails();
		
	}

	public void copyEmployeesWithSalaryResponseDto(EmployeesWithSalaryResponseDto employeesWithSalaryResponseDto) {
		employeeList = employeesWithSalaryResponseDto.getEmployeeList();
		employeeListProcessSalary = employeesWithSalaryResponseDto.getEmployeeListProcessSalary();
		success = employeesWithSalaryResponseDto.isSuccess();	}

	

}
