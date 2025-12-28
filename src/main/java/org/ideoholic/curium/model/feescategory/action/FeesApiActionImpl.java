package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;

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
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentDetailsResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeesApiActionImpl implements FeesApiAction{
	@Autowired
	private FeesService feesService;
	@Autowired
	private StandardService standardService;
	@Autowired
	private StudentService studentService;

	public ResponseEntity<StudentIdDto> applyConcession( ConcessionDto concessionDto, String currentAcademicYear, String branchId, String userId) {
		StudentIdDto studentIdDto = feesService.applyConcession(concessionDto, currentAcademicYear, branchId, userId);
		return ResponseEntity.ok(studentIdDto);
	}

	public ResponseEntity<String> printFeesWaiveoffReport() {
		return ResponseEntity.ok("printfeeswaiveoffreport");
	}

	public ResponseEntity<SearchFeesResponseDto> searchFeesWaiveoffReport(SearchStudentDto searchStudentDto, String branchId ) {
		SearchFeesResponseDto searchFeesResponseDto = feesService.searchFeesWaiveofforConcessionReport(searchStudentDto,"waiveoff",branchId);
		return ResponseEntity.ok(searchFeesResponseDto);
	}

	public ResponseEntity<ResultResponse> feesWaiveoffReport( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);	
		}

	public ResponseEntity<String> printFeesConcessionReport() {
		return ResponseEntity.ok("printfeesconcessionreport");
	}

	public ResponseEntity<SearchFeesResponseDto> searchFeesConcessionReport( SearchStudentDto searchStudentDto,  String branchId ) {
		SearchFeesResponseDto searchFeesResponseDto = feesService.searchFeesWaiveofforConcessionReport(searchStudentDto,"concession",branchId);
		return ResponseEntity.ok(searchFeesResponseDto);
	}

	public ResponseEntity<ResultResponse> feesConcessionReport( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<StudentIdDto> waiveOffFees( ConcessionDto concessionDto, String currentAcademicYear,  String branchId, String userId ) {
		StudentIdDto studentIdDto = feesService.waiveOffFees(concessionDto, currentAcademicYear, branchId, userId);
		return ResponseEntity.ok(studentIdDto);
	}

	public ResponseEntity<FeescategoryDetailDto> feesReport( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result = standardService.viewClasses(branchId);
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		feescategoryDetailDto.copyResultResponse(result);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	public ResponseEntity<ResultResponse> feesStructure( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<StudentIdDto> deleteFeesCategory( ConcessionDto concessionDto,  String branchId, String userId) {
		StudentIdDto studentIdDto = feesService.deleteFeesCategory(concessionDto,branchId,userId);
		return ResponseEntity.ok(studentIdDto);
	}

	public ResponseEntity deleteMultiple( IdFeescategoryDto idFeescategoryDto) {
		feesService.deleteMultiple(idFeescategoryDto);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<FeescategoryDetailDto> feesCollect( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		ParentListResponseDto parentResponseDto = feesService.viewAllStudentsList(branchId);
		feescategoryDetailDto.copyParentListResponseDto(parentResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	public ResponseEntity<FeescategoryDetailDto> feesCollectAllBranches( String branchId, String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		StudentListResponseDto studentListResponseDto = feesService.viewAllBranchStudents();
		feescategoryDetailDto.copyStudentListResponseDto(studentListResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);
	}

	public ResponseEntity addFeesParticular( FeesCategoryDto feesCategoryDto, String branchId,
			 String userLoginId) {
		feesService.addFeesParticular(feesCategoryDto,branchId,userLoginId);
		return ResponseEntity.ok().build();
	}

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
	
	
	public ResponseEntity<ResultResponse> feesDueStampFees( String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity odeleteMultiple( IdFeescategoryDto idFeescategoryDto ) {
		feesService.odeleteMultiple(idFeescategoryDto);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity addotherFeesParticular( OtherFeecategoryDto otherFeecategoryDto, String branchId, String userLoginId) {
		feesService.addOtherFeesParticular(otherFeecategoryDto,branchId,userLoginId);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<OtherFeescategoryDetailDto> otherviewFees( String branchId,
			 String currentAcademicYear) {
		OtherFeescategoryDetailDto otherFeescategoryDetailDto = new OtherFeescategoryDetailDto();
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto=feesService.viewOtherFees(branchId,currentAcademicYear);
		otherFeescategoryDetailDto.copyOtherFeesCategoryResponseDto(otherFeesCategoryResponseDto);
		ResultResponse result = standardService.viewClasses(branchId);
		otherFeescategoryDetailDto.copyResultResponse(result);
		return ResponseEntity.ok(otherFeescategoryDetailDto);
	}
	
	public ResponseEntity<OtherFeescategoryDetailDto> otherfeesCollect( String branchId,
			 String currentAcademicYear) {
		OtherFeescategoryDetailDto otherFeescategoryDetailDto = new OtherFeescategoryDetailDto();
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto=feesService.viewOtherFees(branchId,currentAcademicYear);
		otherFeescategoryDetailDto.copyOtherFeesCategoryResponseDto(otherFeesCategoryResponseDto);
		ParentListResponseDto parentResponseDto = feesService.viewAllStudentsList(branchId);
		otherFeescategoryDetailDto.copyParentListResponseDto(parentResponseDto);
		return ResponseEntity.ok(otherFeescategoryDetailDto);
	}
	
	public ResponseEntity<OtherFeescategoryDetailDto> otherfeesReport( String branchId,
			 String currentAcademicYear) {
		OtherFeescategoryDetailDto otherFeescategoryDetailDto = new OtherFeescategoryDetailDto();
		ResultResponse result=standardService.viewClasses(branchId);
		otherFeescategoryDetailDto.copyResultResponse(result);
		 OtherFeesCategoryResponseDto otherFeesCategoryResponseDto =feesService.viewOtherFees(branchId,currentAcademicYear);
		 otherFeescategoryDetailDto.copyOtherFeesCategoryResponseDto(otherFeesCategoryResponseDto);
		 return ResponseEntity.ok(otherFeescategoryDetailDto);
	}
	
	public ResponseEntity<FeescategoryResponseDto> searchFeeCategory(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr,@RequestHeader(value = "branchid") String branchid,@RequestHeader(value = "feescategories") String feesCategories) throws IOException {
				FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategory(classname,yearofAdmissionStr,yearofAdmissionStr,branchid,feesCategories);
			if(feescategoryResponseDto.isSuccess()) {
				return ResponseEntity.ok(feescategoryResponseDto);
			}
			else {
				throw new CustomResponseException(CustomErrorMessage.ERROR);
			}
	}

	
	public ResponseEntity<StudentIdDto> applyotherConcession( ConcessionDto concessionDto) {
		StudentIdDto studentIdDto = feesService.applyotherConcession(concessionDto);
		return ResponseEntity.ok(studentIdDto);
		}
	

	
	public ResponseEntity<StudentDetailsResponseDto> studentotherFeePage(@RequestParam(value = "id") String studentId,  String branchId) {

		return ResponseEntity.ok(studentService.viewOtherFeesDetailsOfStudent(studentId, branchId));
	}
	
	public ResponseEntity<FeescategoryDetailDto> feesDueReportHeadWise( String branchId,
			 String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result = standardService.viewClasses(branchId);
		feescategoryDetailDto.copyResultResponse(result);
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		 return ResponseEntity.ok(feescategoryDetailDto);
	}
	
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

	public ResponseEntity<FeescategoryDetailDto> defaulterReport( String branchId,
			 String currentAcademicYear) {
		FeescategoryDetailDto feescategoryDetailDto = new FeescategoryDetailDto();
		ResultResponse result =standardService.viewClasses(branchId);
		feescategoryDetailDto.copyResultResponse(result);
		FeescategoryResponseDto feescategoryResponseDto=feesService.viewFees(branchId,currentAcademicYear);
		feescategoryDetailDto.copyFeescategoryResponseDto(feescategoryResponseDto);
		return ResponseEntity.ok(feescategoryDetailDto);

	}

	public ResponseEntity<SearchStudentResponseDto> dndReport( String branchid) {
		SearchStudentResponseDto searchStudentResponseDto = feesService.getDndReport(branchid);
		return ResponseEntity.ok(searchStudentResponseDto);
	}
	
	public ResponseEntity<StudentIdDto> deleteOtherFeesCategory( ConcessionDto concessionDto) {
		StudentIdDto studentIdDto = feesService.deleteOtherFeesCategory(concessionDto);
		return ResponseEntity.ok(studentIdDto);
	}
	
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
