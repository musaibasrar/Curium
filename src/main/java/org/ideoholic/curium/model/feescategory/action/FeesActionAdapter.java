package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.ConcessionDto;
import org.ideoholic.curium.model.feescategory.dto.FeesCategoryDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.IdFeescategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeecategoryDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeesCategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.SearchFeesResponseDto;
import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FeesActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private FeesService feesService;
	

	public String applyConcession() {
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		Map<String, String> allRequestParameters = new HashMap<>();
			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String fieldName = enumeration.nextElement();
				String fieldValue = request.getParameter(fieldName);
				allRequestParameters.put(fieldName, fieldValue);
			}
			concessionDto.setRequestParams(allRequestParameters);

		
			StudentIdDto studentIdDto = feesService.applyConcession(concessionDto,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));
			 String studentId = studentIdDto.getStudentId();
			 return studentId;
			 
	}

	public void searchFeesWaiveofforConcessionReport(String waiveoff) {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		searchStudentDto.setAcademicyear(request.getParameter("academicyear"));
		SearchFeesResponseDto searchFeesResponseDto = feesService.searchFeesWaiveofforConcessionReport(searchStudentDto,waiveoff,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		httpSession.setAttribute("currentyearfromservice",searchFeesResponseDto.getCurrentYearFromService());
		httpSession.setAttribute("studentsfeesstructuredetailswaiveoff", searchFeesResponseDto.getStudentsFeesStructureDetailsWaiveoff());
		httpSession.setAttribute("studentsfeesstructuredetailsconcession", searchFeesResponseDto.getStudentsFeesStructureDetailsConcession());
	}

	public String waiveOffFees() {
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		Map<String, String> allRequestParameters = new HashMap<>();
			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String fieldName = enumeration.nextElement();
				String fieldValue = request.getParameter(fieldName);
				allRequestParameters.put(fieldName, fieldValue);
			}
			concessionDto.setRequestParams(allRequestParameters);

		
			StudentIdDto studentIdDto = feesService.waiveOffFees(concessionDto,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));
			 String studentId = studentIdDto.getStudentId();
			 return studentId;

	}

	public boolean viewFees() {
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR));
		httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
		return feescategoryResponseDto.isSuccess();
	}

	public String deleteFeesCategory() {
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		StudentIdDto studentIdDto = feesService.deleteFeesCategory(concessionDto,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));
		String studentId  = studentIdDto.getStudentId();
		return studentId;
	}

	public void deleteMultiple() {
		IdFeescategoryDto idFeescategoryDto = new IdFeescategoryDto();
		idFeescategoryDto.setIdFeesCategory(request.getParameterValues("idfeescategory"));
		feesService.deleteMultiple(idFeescategoryDto);
		
	}

	public boolean viewAllStudentsList() {
		ParentListResponseDto parentListResponseDto = feesService.viewAllStudentsList(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("studentListFeesCollection", parentListResponseDto.getParentsList());
		return parentListResponseDto.isSuccess();
	}

	public void viewAllBranchStudents() {
		StudentListResponseDto studentListResponseDto = feesService.viewAllBranchStudents();
		request.setAttribute("studentListFeesCollection", studentListResponseDto.getStudentList());
	}

	public void addFeesParticular() {
		FeesCategoryDto feesCategoryDto = new FeesCategoryDto();
		feesCategoryDto.setFromClass(request.getParameterValues("fromclass"));
		feesCategoryDto.setFeesCategory(request.getParameter("feescategory"));
		feesCategoryDto.setAmount(request.getParameter("amount"));
		feesCategoryDto.setCategoryYear(request.getParameter("categoryyear"));
		String installments = request.getParameter("totalinstallments");
		if (StringUtils.hasLength(installments)) {
			feesCategoryDto.setTotalInstallments(Integer.parseInt(installments));
		} else {
			feesCategoryDto.setTotalInstallments(1);
		}

		feesCategoryDto.setMonths(request.getParameterValues("months"));
		feesService.addFeesParticular(feesCategoryDto, DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID), DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));
	}

	public void odeleteMultiple() {
		IdFeescategoryDto idFeescategoryDto = new IdFeescategoryDto();
		idFeescategoryDto.setIdFeesCategory(request.getParameterValues("idfeescategory"));
		feesService.odeleteMultiple(idFeescategoryDto);
	}

	public void addOtherFeesParticular() {
		OtherFeecategoryDto otherFeecategoryDto = new OtherFeecategoryDto();
		otherFeecategoryDto.setFromClass(request.getParameterValues("fromclass"));
		otherFeecategoryDto.setFeesCategory(request.getParameter("feescategory"));
		otherFeecategoryDto.setAmount(request.getParameter("amount"));
		otherFeecategoryDto.setCategoryYearOf(request.getParameter("categoryyearof"));
		feesService.addOtherFeesParticular(otherFeecategoryDto,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));
		
	}

	public boolean viewOtherFees() {
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto = feesService.viewOtherFees(
		DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),
		DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR));
		httpSession.setAttribute("otherfeescategory", otherFeesCategoryResponseDto.getOtherFeesCategory());
		return otherFeesCategoryResponseDto.isSuccess();
	}

	public void getFeeCategory() throws IOException {
		String classname = request.getParameter("classstudying");
    	String yearofAdmission = request.getParameter("yearofadmission");
    	String feesCategories = request.getParameter("feescategories");
    	FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategory(classname,yearofAdmission,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID),feesCategories);
    	httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
	}

	public String applyotherConcession() {
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId( request.getParameter("id"));
		Map<String, String> allRequestParameters = new HashMap<>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String fieldName = enumeration.nextElement();
			String fieldValue = request.getParameter(fieldName);
			allRequestParameters.put(fieldName, fieldValue);
		}
		concessionDto.setRequestParams(allRequestParameters);
		StudentIdDto studentIdDto = feesService.applyotherConcession(concessionDto);
		String studentId = studentIdDto.getStudentId();
		return studentId;
	}

	public void getFeeCategoryHeadWise() throws IOException {
		String classname = request.getParameter("classstudying");
    	String yearofAdmission = request.getParameter("yearofadmission");
    	FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategoryHeadWise(classname,yearofAdmission,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
    	httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
    	httpSession.setAttribute("feesduesearchyear", feescategoryResponseDto.getFeesDueSearchYear());
    	httpSession.setAttribute("feesduesearchclass", feescategoryResponseDto.getFeesDueSearchClass());
	}

	public void getDndReport() {
		SearchStudentResponseDto searchStudentResponseDto = feesService.getDndReport(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("dndStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public String deleteOtherFeesCategory() {
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		StudentIdDto studentIdDto = feesService.deleteOtherFeesCategory(concessionDto);
		return studentIdDto.getStudentId();
	}

	public void viewFeesYearly() throws IOException {
		FeesCategoryDto feesCategoryDto = new FeesCategoryDto();
		String academicYear =request.getParameter("year");
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFeesYearly(academicYear,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		 httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
	}

	public boolean downlaodFile() {
		ResultResponse resultResponse = feesService.downlaodFile();
		return resultResponse.isSuccess();
	}

	public void getOtherFeeCategory()  throws IOException{
		String classname = request.getParameter("classstudying");
    	String yearofAdmission = request.getParameter("yearofadmission");
    	OtherFeesCategoryResponseDto feescategoryResponseDto = feesService.getOtherFeeCategory(classname,yearofAdmission,DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.CURRENTACADEMICYEAR),DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
    	httpSession.setAttribute("otherfeescategory", feescategoryResponseDto.getOtherFeesCategory());
	}

	public void getFeesMonths() {
		FeescategoryResponseDto feescategoryResponseDto = new FeescategoryResponseDto();
		feescategoryResponseDto = feesService.getFeesMonths(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
    	request.setAttribute("feesmonths", feescategoryResponseDto.getFeesMonths());
	}
	
	public boolean viewAllStudentsListOtherFees() {
		ParentListResponseDto parentListResponseDto = feesService.viewAllStudentsListOtherFees(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
		request.setAttribute("studentListFeesCollection", parentListResponseDto.getParentsList());
		return parentListResponseDto.isSuccess();
	}
	
}
