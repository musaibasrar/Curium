package org.ideoholic.curium.model.feescategory.action;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface FeesApiAction {

	 ResponseEntity<StudentIdDto> applyConcession(@RequestBody ConcessionDto concessionDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userId); 

	 ResponseEntity<String> printFeesWaiveoffReport(); 

	 ResponseEntity<SearchFeesResponseDto> searchFeesWaiveoffReport(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId ) ;

	 ResponseEntity<ResultResponse> feesWaiveoffReport(@RequestHeader(value = "branchid") String branchId) ;

	 ResponseEntity<String> printFeesConcessionReport();

	 ResponseEntity<SearchFeesResponseDto> searchFeesConcessionReport(@RequestBody SearchStudentDto searchStudentDto, @RequestHeader(value = "branchid") String branchId );

	 ResponseEntity<ResultResponse> feesConcessionReport(@RequestHeader(value = "branchid") String branchId);

	ResponseEntity<StudentIdDto> waiveOffFees(@RequestBody ConcessionDto concessionDto,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear, @RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userId );

	ResponseEntity<FeescategoryDetailDto> feesReport(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	ResponseEntity<ResultResponse> feesStructure(@RequestHeader(value = "branchid") String branchId);

	ResponseEntity<StudentIdDto> deleteFeesCategory(@RequestBody ConcessionDto concessionDto, @RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userId);

	ResponseEntity deleteMultiple(@RequestBody IdFeescategoryDto idFeescategoryDto);

	ResponseEntity<FeescategoryDetailDto> feesCollect(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	ResponseEntity<FeescategoryDetailDto> feesCollectAllBranches(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	ResponseEntity addFeesParticular(@RequestBody FeesCategoryDto feesCategoryDto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userLoginId);

	ResponseEntity<FeescategoryDetailDto> viewFees(@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	ResponseEntity<StudentDetailsResponseDto> studentFeePage(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);
	
	ResponseEntity<ResultResponse> feesDueStampFees(@RequestHeader(value = "branchid") String branchId);
	
	ResponseEntity odeleteMultiple(@RequestBody IdFeescategoryDto idFeescategoryDto );
	
	ResponseEntity addotherFeesParticular(@RequestBody OtherFeecategoryDto otherFeecategoryDto,@RequestHeader(value = "branchid") String branchId,@RequestHeader(value = "userloginid") String userLoginId);
	
	ResponseEntity<OtherFeescategoryDetailDto> otherviewFees(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
    ResponseEntity<OtherFeescategoryDetailDto> otherfeesCollect(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	ResponseEntity<OtherFeescategoryDetailDto> otherfeesReport(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	ResponseEntity<FeescategoryResponseDto> searchFeeCategory(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr,@RequestHeader(value = "branchid") String branchid,@RequestHeader(value = "feescategories") String feesCategories) throws IOException;

		
    ResponseEntity<StudentIdDto> applyotherConcession(@RequestBody ConcessionDto concessionDto);
	
	ResponseEntity<StudentDetailsResponseDto> studentotherFeePage(@RequestParam(value = "id") String studentId, @RequestHeader(value = "branchid") String branchId);
	
	ResponseEntity<FeescategoryDetailDto> feesDueReportHeadWise(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	ResponseEntity<FeescategoryResponseDto> searchFeeCategoryHeadWise(@RequestParam(value="classname")
	String classname,@RequestParam(value="yearofAdmissionStr") String yearofAdmissionStr,@RequestParam(value="currentAcademicYearStr") String currentAcademicYearStr,@RequestHeader(value = "branchid") String branchid) throws IOException;

	ResponseEntity<FeescategoryDetailDto> defaulterReport(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);

	ResponseEntity<SearchStudentResponseDto> dndReport(@RequestHeader(value = "branchid") String branchid);
	
	ResponseEntity<StudentIdDto> deleteOtherFeesCategory(@RequestBody ConcessionDto concessionDto);
	
	ResponseEntity<FeescategoryDetailDto> feesReportDue(@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
}
