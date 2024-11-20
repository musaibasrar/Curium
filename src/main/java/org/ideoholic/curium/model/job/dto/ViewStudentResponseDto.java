package org.ideoholic.curium.model.job.dto;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.employee.dto.ViewAllEmployeeResponseDto;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewStudentResponseDto {
	
	private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private boolean success=false;
    private List<Student> studentList;
	private List<Parents> parentDetails;


	public void copyViewAllEmployeeResponseDto(ViewAllEmployeeResponseDto viewAllEmployeeResponseDto) {
		employeeList = viewAllEmployeeResponseDto.getEmployeeList();
		employeeListProcessSalary = viewAllEmployeeResponseDto.getEmployeeListProcessSalary();
		success = viewAllEmployeeResponseDto.isSuccess();
		
	}

	public void copyStudentListResponseDto(StudentListResponseDto studentListResponseDto) {
		studentList = studentListResponseDto.getStudentList();
		parentDetails = studentListResponseDto.getParentDetails();
		
	}

	

}
