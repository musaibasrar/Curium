/**
 * 
 */
package org.ideoholic.curium.model.printids.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.employee.dto.PrintMultipleEmployeesResponseDto;
import org.ideoholic.curium.model.employee.dto.ViewAllEmployeeResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.printids.dto.ParentCardResponsDto;
import org.ideoholic.curium.model.printids.dto.PrintIdsDto;
import org.ideoholic.curium.model.printids.service.PrintIdsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/printIds")
public class PrintIdsApiActionImpl implements PrintIdsApiAction {

	
	@Autowired
	private StandardService standardService;
	@Autowired
	private EmployeeService employeeService;	
	@Autowired
	private PrintIdsService printIdsService;


	@PostMapping("/updateCardValidity")
	public ResponseEntity<ResultResponse> updateCardValidity(@RequestBody PrintIdsDto printIdsDto ) {

		ResultResponse result = printIdsService.updateCardValidity(printIdsDto);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/searchDetailsCardValidity")
	public ResponseEntity<ParentCardResponsDto> searchDetailsCardValidity(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId) {

		ParentCardResponsDto result = printIdsService.searchDetailsCardValidity(searchStudentDto,branchId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/cardValidity")
	public ResponseEntity<ResultResponse> cardValidity(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/generateIds")
	public ResponseEntity<ResultResponse> generateIds(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/searchDetails")
	public ResponseEntity<SearchStudentResponseDto> searchDetails(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId) {

		SearchStudentResponseDto result = printIdsService.searchDetails(searchStudentDto,branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/printPreview")
	public ResponseEntity<PrintMultipleEmployeesResponseDto> printPreview(@RequestBody StudentIdsDto studentIdsDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {

		PrintMultipleEmployeesResponseDto result = printIdsService.printMultiple(studentIdsDto,currentAcademicYear);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/generateIdsEmployees")
	public ResponseEntity<ViewAllEmployeeResponseDto> generateIdsEmployees(@RequestHeader(value = "branchid") String branchId) {
		ViewAllEmployeeResponseDto result = employeeService.ViewAllEmployee(branchId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/printPreviewEmployee")
	public ResponseEntity<PrintMultipleEmployeesResponseDto> printpreviewemployee(@RequestBody StudentIdsDto studentIdsDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
		PrintMultipleEmployeesResponseDto result = employeeService.printMultipleEmployees(studentIdsDto,currentAcademicYear);
		return ResponseEntity.ok(result);
	}
	
}
