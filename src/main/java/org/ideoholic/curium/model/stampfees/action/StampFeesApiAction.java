package org.ideoholic.curium.model.stampfees.action;

import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.stampfees.dto.FeesDetailsDto;
import org.ideoholic.curium.model.stampfees.dto.OtherFeesDetailsDto;
import org.ideoholic.curium.model.stampfees.dto.StampFeesDto;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface StampFeesApiAction {


	 ResponseEntity<SearchStudentResponseDto> searchForFees(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId); 

	 ResponseEntity deleteFeesStructure(@RequestBody StudentIdsDto studentIdsDto);
	 
	 ResponseEntity applyFees(@RequestBody StampFeesDto stampFeesDto,@RequestHeader(value = "branchid") String branchId,
				@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
				@RequestHeader(value = "userloginid") String userLoginId);
	 
	 
	 ResponseEntity<FeesDetailsDto> showFeesDetails(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	 ResponseEntity<SearchStudentResponseDto> search(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId );
	
	 ResponseEntity<FeescategoryResponseDto> showFeesDetailsYearly(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	 ResponseEntity<String> applyotherFees(@RequestBody StampFeesDto stampFeesDto,@RequestHeader(value = "branchid") String branchId,
				@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
				@RequestHeader(value = "userloginid") String userLoginId);
	
	 ResponseEntity<OtherFeesDetailsDto> showOtherFeesDetails(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	 ResponseEntity<SearchStudentResponseDto> othersearch(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId); 
	
	 ResponseEntity<FeescategoryResponseDto> advanceSearchForStampFees(@RequestBody SearchStudentDto searchStudentDto,@RequestHeader(value = "branchid") String branchId,
				@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
}
