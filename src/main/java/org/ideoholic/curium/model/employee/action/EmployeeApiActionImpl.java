package org.ideoholic.curium.model.employee.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsDto;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsResponseDto;
import org.ideoholic.curium.model.employee.dto.EmployeeDto;
import org.ideoholic.curium.model.employee.dto.EmployeeIdsDto;
import org.ideoholic.curium.model.employee.dto.EmployeeListDto;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.dto.SearchEmployeeDto;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.employee.dto.ViewAllRelationsResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EmployeeApiActionImpl implements EmployeeApiAction{

    @Autowired
    private EmployeeService employeeService;

    public ResponseEntity<EmployeeListDto> searchEmployee(@RequestBody SearchEmployeeDto dto, @RequestHeader(value = "branchid") String branchId) {
        EmployeeListDto result = employeeService.searchEmployee(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ViewAllRelationsResponseDto> addEmployeePage(@RequestHeader(value = "branchid") String branchId) {
        ViewAllRelationsResponseDto result = employeeService.viewAllRelations(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<EmployeesWithSalaryResponseDto> deleteMultiple(@RequestBody EmployeeIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        employeeService.deleteMultiple(dto);
        return viewEmployee(branchId);
    }

    public ResponseEntity<EmployeeDetailsResponseDto> updateEmployee(@RequestParam("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto employeeDto) {
        Teacher employee = employeeService.updateEmployee(uploadedFiles, employeeDto);
        if (employee.getTid() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return viewDetails(employee.getTid().toString());
    }

    public ResponseEntity<EmployeeDetailsDto> updateEmployeeDetails(@RequestParam(value = "id") String empId, @RequestHeader(value = "branchid") String branchId) {
        EmployeeDetailsDto detailsDto = new EmployeeDetailsDto();

        EmployeeDetailsResponseDto employeeResult = employeeService.viewDetailsEmployee(empId);
        detailsDto.setEmployee(employeeResult.getEmployee());
        detailsDto.setEmployeeLogin(employeeResult.getEmployeeLogin());

        if (employeeResult.isSuccess()) {
            ViewAllRelationsResponseDto relationResult = employeeService.viewAllRelations(branchId);
            detailsDto.setListDepartment(relationResult.getListDepartment());
            detailsDto.setListPosition(relationResult.getListPosition());

            return ResponseEntity.ok(detailsDto);
        } else {
            throw new CustomResponseException(CustomErrorMessage.VIEWALL);
        }
    }

    public ResponseEntity<EmployeeDetailsResponseDto> viewDetails(@RequestParam(value = "id") String empId) {
        EmployeeDetailsResponseDto result = employeeService.viewDetailsEmployee(empId);
        if (empId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<EmployeesWithSalaryResponseDto> viewEmployee(@RequestHeader(value = "branchid") String branchId) {
    	EmployeesWithSalaryResponseDto result = employeeService.viewAllEmployee(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> addEmployee(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "branchcode") String branchCode) {
        ResultResponse result = employeeService.addEmployee(uploadedFiles, dto, branchId, branchCode);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.EMPLOYEENOTSAVED);
        }
    }

}