package org.ideoholic.curium.model.job.dto;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.EmployeeDetailsResponseDto;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.employee.dto.ViewAllEmployeeResponseDto;
import org.ideoholic.curium.model.task.dto.Task;
import org.ideoholic.curium.model.user.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
	
	private Teacher employee;
    private Login employeeLogin;
    private boolean success;
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private List<JobQuery> queriesList;
	private List<Task> taskList;

	public void copyEmployeeDetailsResponseDto(EmployeeDetailsResponseDto employeeDetailsResponseDto) {
		success= employeeDetailsResponseDto.isSuccess();		
	}

	public void copyViewAllEmployeeResponseDto(ViewAllEmployeeResponseDto viewAllEmployeeResponseDto) {
		employeeList = viewAllEmployeeResponseDto.getEmployeeList();
		employeeListProcessSalary = viewAllEmployeeResponseDto.getEmployeeListProcessSalary();
		
	}

	public void copyJobQueryDto(JobQueryDto jobQueryDto) {
		queriesList = jobQueryDto.getQueriesList();
		taskList = jobQueryDto.getTaskList();
		
	}

}
