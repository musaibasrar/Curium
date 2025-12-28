package org.ideoholic.curium.model.feescategory.action;
import java.io.IOException;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.ConcessionDto;
import org.ideoholic.curium.model.feescategory.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryDetailDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.IdFeescategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeescategoryDetailDto;
import org.ideoholic.curium.model.feescategory.dto.SearchFeesResponseDto;
import org.ideoholic.curium.model.student.dto.StudentDetailsResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/feesProcess")
public interface FeesApiAction {

	 @PostMapping("/applyConcession")
	 ResponseEntity<StudentIdDto> applyConcession(@RequestBody ConcessionDto concessionDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userId); 

	 @PostMapping("/printFeesWaiveoffReport")
	 ResponseEntity<String> printFeesWaiveoffReport(); 

	 @PostMapping("/searchFeesWaiveoffReport")
	 ResponseEntity<SearchFeesResponseDto> searchFeesWaiveoffReport(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId ) ;

	 @GetMapping("/feesWaiveoffReport")
	 ResponseEntity<ResultResponse> feesWaiveoffReport(@RequestHeader(value = "branchid") String branchId) ;

	 @PostMapping("/printFeesConcessionReport")
	 ResponseEntity<String> printFeesConcessionReport();

	 @PostMapping("/searchFeesConcessionReport")
	 ResponseEntity<SearchFeesResponseDto> searchFeesConcessionReport(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId );

	 @GetMapping("/feesConcessionReport")
	 ResponseEntity<ResultResponse> feesConcessionReport(@RequestHeader(value = "branchid") String branchId);

	 @PostMapping("/waiveOffFees")
	ResponseEntity<StudentIdDto> waiveOffFees(@RequestBody ConcessionDto concessionDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userId );

	 @GetMapping("/feesReport")
	ResponseEntity<FeescategoryDetailDto> feesReport(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

		@GetMapping("/feesStructure")
	ResponseEntity<ResultResponse> feesStructure(@RequestHeader(value = "branchid") String branchId);

		@PostMapping("/deleteFeesCategory")
	ResponseEntity<StudentIdDto> deleteFeesCategory(@RequestBody ConcessionDto concessionDto, @RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userId);

		@PostMapping("/deleteMultiple")
	ResponseEntity deleteMultiple(@RequestBody IdFeescategoryDto idFeescategoryDto);

		@GetMapping("/feesCollect")
	ResponseEntity<FeescategoryDetailDto> feesCollect(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

		@GetMapping("/feesCollectAllBranches")
	ResponseEntity<FeescategoryDetailDto> feesCollectAllBranches(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

		@PostMapping("/addFeesParticular")
	ResponseEntity addFeesParticular(@RequestBody FeesCategoryDto feesCategoryDto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userLoginId);

		@GetMapping("/feesView")
	ResponseEntity<FeescategoryDetailDto> viewFees(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	ResponseEntity<StudentDetailsResponseDto> studentFeePage(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);
	
	@GetMapping("/feesDueStampFees")
	ResponseEntity<ResultResponse> feesDueStampFees(@RequestHeader(value = "branchid") String branchId);
	
	@PostMapping("/odeleteMultiple")
	ResponseEntity odeleteMultiple(@RequestBody IdFeescategoryDto idFeescategoryDto );
	
	@PostMapping("/addotherFeesParticular")
	ResponseEntity addotherFeesParticular(@RequestBody OtherFeecategoryDto otherFeecategoryDto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userLoginId);
	
	@GetMapping("/otherFeesView")
	ResponseEntity<OtherFeescategoryDetailDto> otherviewFees(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	@GetMapping("/otherfeesCollect")
    ResponseEntity<OtherFeescategoryDetailDto> otherfeesCollect(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	@GetMapping("/otherfeesReport")
	ResponseEntity<OtherFeescategoryDetailDto> otherfeesReport(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	@GetMapping("/searchfeecategory")
	ResponseEntity<FeescategoryResponseDto> searchFeeCategory(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr,@RequestHeader(value = "branchid") String branchid,@RequestHeader(value = "feescategories") String feesCategories) throws IOException;

	@PostMapping("/applyotherConcession")	
    ResponseEntity<StudentIdDto> applyotherConcession(@RequestBody ConcessionDto concessionDto);

	ResponseEntity<StudentDetailsResponseDto> studentotherFeePage(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);

	@GetMapping("/feesDueReportHeadWise")
	ResponseEntity<FeescategoryDetailDto> feesDueReportHeadWise(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	@GetMapping("/searchfeecategoryheadwise")
	ResponseEntity<FeescategoryResponseDto> searchFeeCategoryHeadWise(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr,@RequestHeader(value = "branchid") String branchid) throws IOException;

	@GetMapping("/defaulterReport")
	ResponseEntity<FeescategoryDetailDto> defaulterReport(@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	@GetMapping("/dndReport")
	ResponseEntity<SearchStudentResponseDto> dndReport(@RequestHeader(value = "branchid") String branchid);

	@PostMapping("/deleteOtherFeesCategory")
	ResponseEntity<StudentIdDto> deleteOtherFeesCategory(@RequestBody ConcessionDto concessionDto);

	@GetMapping("/feesReportDue")
	ResponseEntity<FeescategoryDetailDto> feesReportDue(@RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
}
