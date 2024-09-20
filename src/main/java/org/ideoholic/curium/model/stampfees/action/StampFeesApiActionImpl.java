package org.ideoholic.curium.model.stampfees.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeesCategoryResponseDto;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.stampfees.dto.FeesDetailsDto;
import org.ideoholic.curium.model.stampfees.dto.OtherFeesDetailsDto;
import org.ideoholic.curium.model.stampfees.dto.StampFeesDto;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
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
@RequestMapping("/api/v1/stampFeesProcess")
public class StampFeesApiActionImpl implements StampFeesApiAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private YearService yearService;
	@Autowired
	private StandardService standardService;
	
	@Autowired
	private FeesService feesService;

	@Autowired
	private StampFeesService stampFeesService;

	@PostMapping("/searchForFees")
	public ResponseEntity<SearchStudentResponseDto> searchForFees(@RequestBody SearchStudentDto searchStudentDto,
			@RequestHeader(value = "branchid") String branchId) {
		SearchStudentResponseDto result = stampFeesService.advanceSearch(searchStudentDto, branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/delete")
	public ResponseEntity deleteFeesStructure(@RequestBody StudentIdsDto studentIdsDto) {
		stampFeesService.deleteFeesStamp(studentIdsDto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/applyFees")
	public ResponseEntity applyFees(@RequestBody StampFeesDto stampFeesDto,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId) {
		stampFeesService.addFeesStamp(stampFeesDto,currentAcademicYear, branchId,  userLoginId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/showFeesDetails")
	public ResponseEntity<FeesDetailsDto> showFeesDetails(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
		FeesDetailsDto feesDetailDto = new FeesDetailsDto();
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId, currentAcademicYear);
		Currentacademicyear currentacademicyear = yearService.getYear();
		ResultResponse result = standardService.viewClasses(branchId);
		feesDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		feesDetailDto.copyCurrentacademicyear(currentacademicyear);
		feesDetailDto.setSuccess(result.isSuccess());
		return ResponseEntity.ok(feesDetailDto);
	}

	@PostMapping("/search")
	public ResponseEntity<SearchStudentResponseDto> search(@RequestBody SearchStudentDto searchStudentDto,
			@RequestHeader(value = "branchid") String branchId) {
		SearchStudentResponseDto result = stampFeesService.advanceSearch(searchStudentDto, branchId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/showFeesDetailsYearly")
	public ResponseEntity<FeescategoryResponseDto> showFeesDetailsYearly(
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
		FeescategoryResponseDto result;
		try {
			result = feesService.viewFeesYearly(branchId, currentAcademicYear);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/applyotherFees")
	public ResponseEntity<String> applyotherFees(@RequestBody StampFeesDto stampFeesDto,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId) {
		stampFeesService.addotherFeesStamp(stampFeesDto,currentAcademicYear, branchId,  userLoginId);
		return ResponseEntity.ok("feesstampsuccess");
	}

	@GetMapping("/showOtherFeesDetails")
	public ResponseEntity<OtherFeesDetailsDto> showOtherFeesDetails(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
		OtherFeesDetailsDto otherFeesDetailsDto = new OtherFeesDetailsDto();
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto = feesService.viewOtherFees(branchId,
				currentAcademicYear);
		Currentacademicyear currentacademicyear = yearService.getYear();
		ResultResponse result = standardService.viewClasses(branchId);
		otherFeesDetailsDto.setOtherFeecategory(otherFeesCategoryResponseDto.getOtherFeesCategory());
		otherFeesDetailsDto.setCurrentacademicyear(currentacademicyear.getCurrentacademicyear());
		otherFeesDetailsDto.setSuccess(result.isSuccess());
		return ResponseEntity.ok(otherFeesDetailsDto);
	}

	@PostMapping("/othersearch")
	public ResponseEntity<SearchStudentResponseDto> othersearch(@RequestBody SearchStudentDto searchStudentDto,
			@RequestHeader(value = "branchid") String branchId) {
		SearchStudentResponseDto result = stampFeesService.otheradvanceSearch(searchStudentDto, branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/advanceSearchForStampFees")
	public ResponseEntity<FeescategoryResponseDto> advanceSearchForStampFees(
			@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear) {
		FeescategoryResponseDto result = stampFeesService.advanceSearchForStampFees(searchStudentDto, branchId,
				currentAcademicYear);
		return ResponseEntity.ok(result);
	}

	

}
