package org.ideoholic.curium.model.employee.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.employee.dto.*;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/employeeProcess")
public class EmployeeApiActionImpl implements EmployeeApiAction{

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/searchEmployee")
    public ResponseEntity<EmployeeListDto> searchEmployee(@RequestBody SearchEmployeeDto dto, @RequestHeader(value = "branchid") String branchId) {
        EmployeeListDto result = employeeService.searchEmployee(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/addEmployeePage")
    public ResponseEntity<ViewAllRelationsResponseDto> addEmployeePage(@RequestHeader(value = "branchid") String branchId) {
        ViewAllRelationsResponseDto result = employeeService.viewAllRelations(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<ViewAllEmployeeResponseDto> deleteMultiple(@RequestBody EmployeeIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        employeeService.deleteMultiple(dto);
        return viewEmployee(branchId);
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDetailsResponseDto> updateEmployee(@RequestParam("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto employeeDto) {
        Teacher employee = employeeService.updateEmployee(uploadedFiles, employeeDto);
        if (employee.getTid() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return viewDetails(employee.getTid().toString());
    }

    @PostMapping("/updateEmployeeDetails")
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

    @GetMapping("/ViewDetails")
    public ResponseEntity<EmployeeDetailsResponseDto> viewDetails(@RequestParam(value = "id") String empId) {
        EmployeeDetailsResponseDto result = employeeService.viewDetailsEmployee(empId);
        if (empId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/ViewAllEmployee", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ViewAllEmployeeResponseDto> viewEmployee(@RequestHeader(value = "branchid") String branchId) {
        ViewAllEmployeeResponseDto result = employeeService.ViewAllEmployee(branchId);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResultResponse> addEmployee(@RequestPart("fileToUpload") MultipartFile[] uploadedFiles, @RequestPart("employeeDto") EmployeeDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "branchcode") String branchCode) {
        ResultResponse result = employeeService.addEmployee(uploadedFiles, dto, branchId, branchCode);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        } else {
            throw new CustomResponseException(CustomErrorMessage.EMPLOYEENOTSAVED);
        }
    }

}