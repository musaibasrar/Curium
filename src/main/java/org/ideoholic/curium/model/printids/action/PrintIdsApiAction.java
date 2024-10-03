/**
 * 
 */
package org.ideoholic.curium.model.printids.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.employee.dto.PrintMultipleEmployeesResponseDto;
import org.ideoholic.curium.model.employee.dto.ViewAllEmployeeResponseDto;
import org.ideoholic.curium.model.printids.dto.ParentCardResponsDto;
import org.ideoholic.curium.model.printids.dto.PrintIdsDto;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface PrintIdsApiAction {

	


	 ResponseEntity<ResultResponse> updateCardValidity(@RequestBody PrintIdsDto printIdsDto );

	ResponseEntity<ParentCardResponsDto> searchDetailsCardValidity(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId);

	ResponseEntity<ResultResponse> cardValidity(@RequestHeader(value = "branchid") String branchId);

	ResponseEntity<ResultResponse> generateIds(@RequestHeader(value = "branchid") String branchId);

	ResponseEntity<SearchStudentResponseDto> searchDetails(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId);

	ResponseEntity<PrintMultipleEmployeesResponseDto> printPreview(@RequestBody StudentIdsDto studentIdsDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	ResponseEntity<ViewAllEmployeeResponseDto> generateIdsEmployees(@RequestHeader(value = "branchid") String branchId);
	
	ResponseEntity<PrintMultipleEmployeesResponseDto> printpreviewemployee(@RequestBody StudentIdsDto studentIdsDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
}