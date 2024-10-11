package org.ideoholic.curium.model.feescategory.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private String BRANCHID = "branchid";
   
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	
	private String USERID = "userloginid";
	

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

		
			StudentIdDto studentIdDto = feesService.applyConcession(concessionDto);
			 String studentId = studentIdDto.getStudentId();
			 return studentId;
			 
	}

	public void searchFeesWaiveofforConcessionReport(String waiveoff) {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		SearchFeesResponseDto searchFeesResponseDto = feesService.searchFeesWaiveofforConcessionReport(searchStudentDto,waiveoff,httpSession.getAttribute("branchid").toString());
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

		
			StudentIdDto studentIdDto = feesService.waiveOffFees(concessionDto);
			 String studentId = studentIdDto.getStudentId();
			 return studentId;

	}

	public boolean viewFees() {
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(httpSession.getAttribute("branchid").toString(),httpSession.getAttribute("currentAcademicYear").toString());
		httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
		return feescategoryResponseDto.isSuccess();
	}

	public String deleteFeesCategory() {
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		StudentIdDto studentIdDto = feesService.deleteFeesCategory(concessionDto,httpSession.getAttribute("branchid").toString(),httpSession.getAttribute(USERID).toString());
		String studentId  = studentIdDto.getStudentId();
		return studentId;
	}

	public void deleteMultiple() {
		IdFeescategoryDto idFeescategoryDto = new IdFeescategoryDto();
		idFeescategoryDto.setIdFeesCategory(request.getParameterValues("idfeescategory"));
		feesService.deleteMultiple(idFeescategoryDto);
		
	}

	public boolean viewAllStudentsList() {
		ParentListResponseDto parentListResponseDto = feesService.viewAllStudentsList(httpSession.getAttribute("branchid").toString());
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
		feesService.addFeesParticular(feesCategoryDto,httpSession.getAttribute("branchid").toString(),httpSession.getAttribute("userloginid").toString());
	}

	public void odeleteMultiple() {
		IdFeescategoryDto idFeescategoryDto = new IdFeescategoryDto();
		idFeescategoryDto.setIdFeesCategory(request.getParameterValues("idfeescategory"));
		feesService.odeleteMultiple(idFeescategoryDto);
	}

	public void addOtherFeesParticular() {
		OtherFeecategoryDto otherFeecategoryDto = new OtherFeecategoryDto();
		otherFeecategoryDto.setFeesCategory(request.getParameter("feescategory"));
		otherFeecategoryDto.setFromClass(request.getParameter("fromclass"));
		otherFeecategoryDto.setToClass(request.getParameter("toclass"));
		otherFeecategoryDto.setAmount(request.getParameter("amount"));
		otherFeecategoryDto.setCategoryYearOf(request.getParameter("categoryyearof"));
		feesService.addOtherFeesParticular(otherFeecategoryDto,httpSession.getAttribute("branchid").toString(),httpSession.getAttribute("userloginid").toString());
		
	}

	public boolean viewOtherFees() {
		OtherFeesCategoryResponseDto otherFeesCategoryResponseDto = feesService.viewOtherFees(
		httpSession.getAttribute(BRANCHID).toString(),
		httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		httpSession.setAttribute("otherfeescategory", otherFeesCategoryResponseDto.getOtherFeesCategory());
		return otherFeesCategoryResponseDto.isSuccess();
	}

	public void getFeeCategory() throws IOException {
		String classname = request.getParameter("classstudying");
    	String yearofAdmission = request.getParameter("yearofadmission");
    	FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategory(classname,yearofAdmission,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString());
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
    	FeescategoryResponseDto feescategoryResponseDto = feesService.getFeeCategoryHeadWise(classname,yearofAdmission,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString());
    	httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
	}

	public void getDndReport() {
		SearchStudentResponseDto searchStudentResponseDto = feesService.getDndReport(httpSession.getAttribute(BRANCHID).toString());
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
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFeesYearly(academicYear,httpSession.getAttribute(BRANCHID).toString());
		 httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
	}
	
}
