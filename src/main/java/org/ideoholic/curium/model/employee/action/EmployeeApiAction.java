package org.ideoholic.curium.model.employee.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeApiAction {

    ResponseEntity<EmployeeListDto> searchEmployee(@RequestBody SearchEmployeeDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ViewAllRelationsResponseDto> addEmployeePage(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ViewAllEmployeeResponseDto> deleteMultiple(@RequestBody EmployeeIdsDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<EmployeeDetailsResponseDto> updateEmployee(@RequestParam("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto employeeDto);

    ResponseEntity<EmployeeDetailsDto> updateEmployeeDetails(@RequestParam(value = "id") String empId, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<EmployeeDetailsResponseDto> viewDetails(@RequestParam(value = "id") String empId);

    ResponseEntity<ViewAllEmployeeResponseDto> viewEmployee(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> addEmployee(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "branchcode") String branchCode);
}
