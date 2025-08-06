package org.ideoholic.curium.model.employee.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsDto;
import org.ideoholic.curium.model.employee.dto.EmployeeDetailsResponseDto;
import org.ideoholic.curium.model.employee.dto.EmployeeDto;
import org.ideoholic.curium.model.employee.dto.EmployeeIdsDto;
import org.ideoholic.curium.model.employee.dto.EmployeeListDto;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.dto.SearchEmployeeDto;
import org.ideoholic.curium.model.employee.dto.ViewAllRelationsResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/employeeProcess")
interface EmployeeApiAction {

	@PostMapping("/searchEmployee")
	ResponseEntity<EmployeeListDto> searchEmployee(@RequestBody SearchEmployeeDto dto, @RequestHeader(value = "branchid") String branchId);

	@GetMapping("/addEmployeePage")
	ResponseEntity<ViewAllRelationsResponseDto> addEmployeePage(@RequestHeader(value = "branchid") String branchId);

	@PostMapping("/deleteMultiple")
	ResponseEntity<EmployeesWithSalaryResponseDto> deleteMultiple(@RequestBody EmployeeIdsDto dto, @RequestHeader(value = "branchid") String branchId);

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	ResponseEntity<EmployeeDetailsResponseDto> updateEmployee(@RequestParam("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto employeeDto);

	@PostMapping("/updateEmployeeDetails")
	ResponseEntity<EmployeeDetailsDto> updateEmployeeDetails(@RequestParam(value = "id") String empId, @RequestHeader(value = "branchid") String branchId);

	@GetMapping("/ViewDetails")
	ResponseEntity<EmployeeDetailsResponseDto> viewDetails(@RequestParam(value = "id") String empId);

	@RequestMapping(value = "/ViewAllEmployee", method = { RequestMethod.GET, RequestMethod.POST })
	ResponseEntity<EmployeesWithSalaryResponseDto> viewEmployee(@RequestHeader(value = "branchid") String branchId);

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseEntity<ResultResponse> addEmployee(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "branchcode") String branchCode);

}
