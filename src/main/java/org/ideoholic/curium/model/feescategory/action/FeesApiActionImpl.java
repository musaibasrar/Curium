package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.ConcessionDto;
import org.ideoholic.curium.model.feescategory.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryDetailDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.IdFeescategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeesCategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeescategoryDetailDto;
import org.ideoholic.curium.model.feescategory.dto.SearchFeesResponseDto;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentDetailsResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeesApiActionImpl implements FeesApiAction{
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private FeesService feesService;
	@Autowired
	private StandardService standardService;
	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private StudentService studentService;

	public ResponseEntity<StudentIdDto> applyConcession( ConcessionDto concessionDto, String currentAcademicYear,  String branchId, String userId) {
		StudentIdDto studentIdDto = feesService.applyConcession(concessionDto, currentAcademicYear, branchId, userId);
		return ResponseEntity.ok(studentIdDto);
	}

	@PostMapping("/printFeesWaiveoffReport")
	public ResponseEntity<String> printFeesWaiveoffReport() {
		return ResponseEntity.ok("printfeeswaiveoffreport");
	}

	@PostMapping("/searchFeesWaiveoffReport")
	public ResponseEntity<SearchFeesResponseDto> searchFeesWaiveoffReport( SearchStudentDto searchStudentDto,  String branchId ) {
		SearchFeesResponseDto searchFeesResponseDto = feesService.searchFeesWaiveofforConcessionReport(searchStudentDto,"waiveoff",branchId);
		return ResponseEntity.ok(searchFeesResponseDto);
	}

	@GetMapping("/feesWaiveoffReport")
	public ResponseEntity<ResultResponse> feesWaiveoffReport( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);	
		}

	@PostMapping("/printFeesConcessionReport")
	public ResponseEntity<String> printFeesConcessionReport() {
		return ResponseEntity.ok("printfeesconcessionreport");
	}

	@PostMapping("/searchFeesConcessionReport")
	public ResponseEntity<SearchFeesResponseDto> searchFeesConcessionReport( SearchStudentDto searchStudentDto,  String branchId ) {
		SearchFeesResponseDto searchFeesResponseDto = feesService.searchFeesWaiveofforConcessionReport(searchStudentDto,"concession",branchId);
		return ResponseEntity.ok(searchFeesResponseDto);
	}

	@GetMapping("/feesConcessionReport")
	public ResponseEntity<ResultResponse> feesConcessionReport( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/waiveOffFees")
	public ResponseEntity<StudentIdDto> waiveOffFees( ConcessionDto concessionDto, String currentAcademicYear,  String branchId, String userId ) {
		StudentIdDto studentIdDto = feesService.waiveOffFees(concessionDto, currentAcademicYear, branchId, userId);
		return ResponseEntity.ok(studentIdDto);
	}

	@GetMapping("/feesReport")
	public ResponseEntity<FeescategoryDetailDto> feesReport( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result = standardService.viewClasses(branchId);
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		feescategoryDetailDto.copyResultResponse(result);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	@GetMapping("/feesStructure")
	public ResponseEntity<ResultResponse> feesStructure( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/deleteFeesCategory")
	public ResponseEntity<StudentIdDto> deleteFeesCategory( ConcessionDto concessionDto,  String branchId, String userId) {
		StudentIdDto studentIdDto = feesService.deleteFeesCategory(concessionDto,branchId,userId);
		return ResponseEntity.ok(studentIdDto);
	}

	@PostMapping("/deleteMultiple")
	public ResponseEntity deleteMultiple( IdFeescategoryDto idFeescategoryDto) {
		feesService.deleteMultiple(idFeescategoryDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/feesCollect")
	public ResponseEntity<FeescategoryDetailDto> feesCollect( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		ParentListResponseDto parentResponseDto = feesService.viewAllStudentsList(branchId);
		feescategoryDetailDto.copyParentListResponseDto(parentResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	@GetMapping("/feesCollectAllBranches")
	public ResponseEntity<FeescategoryDetailDto> feesCollectAllBranches( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		StudentListResponseDto studentListResponseDto = feesService.viewAllBranchStudents();
		feescategoryDetailDto.copyStudentListResponseDto(studentListResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	@PostMapping("/addFeesParticular")
	public ResponseEntity addFeesParticular( FeesCategoryDto feesCategoryDto, String branchId,
			 String userLoginId) {
		feesService.addFeesParticular(feesCategoryDto,branchId,userLoginId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/feesView")
	public ResponseEntity<FeescategoryDetailDto> viewFees( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		ResultResponse result = standardService.viewClasses(branchId);
		feescategoryDetailDto.copyResultResponse(result);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	public ResponseEntity<StudentDetailsResponseDto> studentFeePage( String studentId,  String branchId) {
		
		return ResponseEntity.ok(studentService.viewDetailsOfStudent(studentId, branchId));
	}
	
	
	@GetMapping("/feesDueStampFees")
	public ResponseEntity<ResultResponse> feesDueStampFees( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/odeleteMultiple")
	public ResponseEntity odeleteMultiple( IdFeescategoryDto idFeescategoryDto ) {
		feesService.odeleteMultiple(idFeescategoryDto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/addotherFeesParticular")
	public ResponseEntity addotherFeesParticular( OtherFeecategoryDto otherFeecategoryDto, String branchId, String userLoginId) {
		feesService.addOtherFeesParticular(otherFeecategoryDto,branchId,userLoginId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/otherFeesView")
	public ResponseEntity<OtherFeescategoryDetailDto> otherviewFees( String branchId,
			 String currentAcademicYear) {
		OtherFeescategoryDetailDto otherFeescategoryDetailDto = new OtherFeescategoryDetailDto();
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto=feesService.viewOtherFees(branchId,currentAcademicYear);
		otherFeescategoryDetailDto.copyOtherFeesCategoryResponseDto(otherFeesCategoryResponseDto);
		ResultResponse result = standardService.viewClasses(branchId);
		otherFeescategoryDetailDto.copyResultResponse(result);
		return ResponseEntity.ok(otherFeescategoryDetailDto);
	}
	
	@GetMapping("/otherfeesCollect")
	public ResponseEntity<OtherFeescategoryDetailDto> otherfeesCollect( String branchId,
			 String currentAcademicYear) {
		OtherFeescategoryDetailDto otherFeescategoryDetailDto = new OtherFeescategoryDetailDto();
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto=feesService.viewOtherFees(branchId,currentAcademicYear);
		otherFeescategoryDetailDto.copyOtherFeesCategoryResponseDto(otherFeesCategoryResponseDto);
		ParentListResponseDto parentResponseDto = feesService.viewAllStudentsList(branchId);
		otherFeescategoryDetailDto.copyParentListResponseDto(parentResponseDto);
		return ResponseEntity.ok(otherFeescategoryDetailDto);
	}
	
	@GetMapping("/otherfeesReport")
	public ResponseEntity<OtherFeescategoryDetailDto> otherfeesReport( String branchId,
			 String currentAcademicYear) {
		OtherFeescategoryDetailDto otherFeescategoryDetailDto = new OtherFeescategoryDetailDto();
		ResultResponse result=standardService.viewClasses(branchId);
		otherFeescategoryDetailDto.copyResultResponse(result);
		 OtherFeesCategoryResponseDto otherFeesCategoryResponseDto =feesService.viewOtherFees(branchId,currentAcademicYear);
		 otherFeescategoryDetailDto.copyOtherFeesCategoryResponseDto(otherFeesCategoryResponseDto);
		 return ResponseEntity.ok(otherFeescategoryDetailDto);
	}
	
	@GetMapping("/searchfeecategory")
	public ResponseEntity<FeescategoryResponseDto> searchFeeCategory(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr, String branchid) throws IOException {
				FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategory(classname,yearofAdmissionStr,yearofAdmissionStr,branchid);
			if(feescategoryResponseDto.isSuccess()) {
				return ResponseEntity.ok(feescategoryResponseDto);
			}
			else {
				throw new CustomResponseException(CustomErrorMessage.ERROR);
			}
	}

	
	
		
	@PostMapping("/applyotherConcession")
	public ResponseEntity<StudentIdDto> applyotherConcession( ConcessionDto concessionDto) {
		StudentIdDto studentIdDto = feesService.applyotherConcession(concessionDto);
		return ResponseEntity.ok(studentIdDto);
		}
	

	
	public ResponseEntity<StudentDetailsResponseDto> studentotherFeePage(@RequestParam(value = "id") String studentId,  String branchId) {

		return ResponseEntity.ok(studentService.viewOtherFeesDetailsOfStudent(studentId, branchId));
	}
	
	@GetMapping("/feesDueReportHeadWise")
	public ResponseEntity<FeescategoryDetailDto> feesDueReportHeadWise( String branchId,
			 String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result = standardService.viewClasses(branchId);
		feescategoryDetailDto.copyResultResponse(result);
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		 return ResponseEntity.ok(feescategoryDetailDto);
	}
	
	@GetMapping("/searchfeecategoryheadwise")
	public ResponseEntity<FeescategoryResponseDto> searchFeeCategoryHeadWise(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr, String branchid) throws IOException {
		FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategoryHeadWise(classname,yearofAdmissionStr,currentAcademicYearStr,branchid);
		if(feescategoryResponseDto.isSuccess())	{
			return ResponseEntity.ok(feescategoryResponseDto);
		}
		else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@GetMapping("/defaulterReport")
	public ResponseEntity<FeescategoryDetailDto> defaulterReport( String branchId,
			 String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result =standardService.viewClasses(branchId);
		feescategoryDetailDto.copyResultResponse(result);
		FeescategoryResponseDto feescategoryResponseDto=feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);

	}

	@GetMapping("/dndReport")
	public ResponseEntity<SearchStudentResponseDto> dndReport( String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = feesService.getDndReport(branchid);
		return ResponseEntity.ok(searchStudentResponseDto);
	}
	
	@PostMapping("/deleteOtherFeesCategory")
	public ResponseEntity<StudentIdDto> deleteOtherFeesCategory( ConcessionDto concessionDto) {
		StudentIdDto studentIdDto = feesService.deleteOtherFeesCategory(concessionDto);
		return ResponseEntity.ok(studentIdDto);
	}
	
	@GetMapping("/feesReportDue")
	public ResponseEntity<FeescategoryDetailDto> feesReportDue( String branchId,
			 String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result = standardService.viewClasses(branchId);
		feescategoryDetailDto.copyResultResponse(result);
		FeescategoryResponseDto feescategoryResponseDto=feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);

	}

	

	}
