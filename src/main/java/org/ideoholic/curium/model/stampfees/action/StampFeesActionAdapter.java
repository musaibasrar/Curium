package org.ideoholic.curium.model.stampfees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.OtherFeesCategoryResponseDto;
import org.ideoholic.curium.model.stampfees.dto.StampFeesDto;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StampFeesActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private StampFeesService stampFeesService;
	
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private String CURRENTACADEMICYEAR = "currentAcademicYear";

	public void advanceSearch() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		SearchStudentResponseDto searchStudentResponseDto = stampFeesService.advanceSearch(searchStudentDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void deleteFeesStamp() {
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setCurrentYear(request.getParameter("currentyear"));
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesService.deleteFeesStamp(studentIdsDto); 
		
	}

	public void addFeesStamp() {
		StampFeesDto stampFeesDto = new StampFeesDto();
		stampFeesDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesDto.setFeesTotalAmount(request.getParameter("feesTotalAmount"));
		stampFeesDto.setFeesCategoryIds(request.getParameterValues("feesIDS"));
		stampFeesDto.setFeesAmount(request.getParameterValues("feesFullCat"));
		stampFeesDto.setConcession(request.getParameterValues("feesConcession"));
		stampFeesDto.setTotalInstallments(request.getParameterValues("feesCount"));
		stampFeesDto.setFeesYears(request.getParameterValues("feesYears"));
		stampFeesService.addFeesStamp(stampFeesDto,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());
		
	}

	public void addotherFeesStamp() {
		StampFeesDto stampFeesDto = new StampFeesDto();
		stampFeesDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesDto.setFeesTotalAmount(request.getParameter("feesTotalAmount"));
		stampFeesDto.setFeesCategoryIds(request.getParameterValues("feesIDS"));
		stampFeesDto.setFeesAmount(request.getParameterValues("feesFullCat"));
		stampFeesDto.setConcession(request.getParameterValues("feesConcession"));
		stampFeesDto.setTotalInstallments(request.getParameterValues("feesCount"));
		stampFeesDto.setFeesYears(request.getParameterValues("feesYears"));
		stampFeesService.addotherFeesStamp(stampFeesDto,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());
		
	}

	public void otheradvanceSearch() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		OtherFeesCategoryResponseDto otherFeescategoryResponseDto = stampFeesService.otheradvanceSearch(searchStudentDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		request.setAttribute("searchStudentList", otherFeescategoryResponseDto.getSearchStudentList());
		httpSession.setAttribute("otherfeescategory", otherFeescategoryResponseDto.getOtherFeesCategory());
	}

	public void advanceSearchForStampFees() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		searchStudentDto.setStudentType(request.getParameter("studenttype"));
		searchStudentDto.setCategoryYear(request.getParameter("categoryyear"));
		FeescategoryResponseDto feescategoryResponseDto = stampFeesService.advanceSearchForStampFees(searchStudentDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
		request.setAttribute("searchStudentList", feescategoryResponseDto.getSearchStudentList());
	}

	public void multiClassSearch() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setClassesSearch(request.getParameterValues("classsearch"));
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		searchStudentDto.setAcademicyear(request.getParameter("academicyear"));
		SearchStudentResponseDto searchStudentResponseDto = stampFeesService.multiClassSearch(searchStudentDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void advanceSearchForStampFeesByCategory() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setClassesSearch(request.getParameterValues("classesSearch"));
		searchStudentDto.setNameSearch(request.getParameter("feescategorysearch"));
		searchStudentDto.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		FeescategoryResponseDto feescategoryResponseDto = stampFeesService.advanceSearchForStampFeesByCategory(searchStudentDto,httpSession.getAttribute(BRANCHID).toString());
		httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
		httpSession.setAttribute("searchStudentList", feescategoryResponseDto.getSearchStudentList());
	}

	public void addFeesStampAll() {
		StampFeesDto stampFeesDto = new StampFeesDto();
		stampFeesDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesDto.setFeesTotalAmount(request.getParameter("feesTotalAmount"));
		stampFeesDto.setFeesCategoryIds(request.getParameterValues("feesIDS"));
		stampFeesDto.setFeesAmount(request.getParameterValues("feesFullCat"));
		stampFeesDto.setConcession(request.getParameterValues("feesConcession"));
		stampFeesDto.setTotalInstallments(request.getParameterValues("feesCount"));
		stampFeesDto.setFeesYears(request.getParameterValues("feesYears"));
		stampFeesService.addFeesStampAll(stampFeesDto,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());
		
	}
}
