package org.ideoholic.curium.model.stampfees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
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
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private String CURRENTACADEMICYEAR = "currentAcademicYear";

	public void advanceSearch() {
		StampFeesService stampFeesService = new StampFeesService(request, response);
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		SearchStudentResponseDto searchStudentResponseDto = stampFeesService.advanceSearch(searchStudentDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void deleteFeesStamp() {
		StampFeesService stampFeesService = new StampFeesService(request, response);
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setCurrentYear(request.getParameter("currentyear"));
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesService.deleteFeesStamp(studentIdsDto); 
		
	}

	public void addFeesStamp() {
		StampFeesService stampFeesService = new StampFeesService(request, response);
		StampFeesDto stampFeesDto = new StampFeesDto();
		stampFeesDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesDto.setFeesTotalAmount(request.getParameter("feesTotalAmount"));
		stampFeesDto.setFeesCategoryIds(request.getParameterValues("feesIDS"));
		stampFeesDto.setFeesAmount(request.getParameterValues("fessFullCat"));
		stampFeesDto.setConcession(request.getParameterValues("feesConcession"));
		stampFeesDto.setTotalInstallments(request.getParameterValues("feesCount"));
		stampFeesDto.setFeesYears(request.getParameterValues("feesYears"));
		stampFeesService.addFeesStamp(stampFeesDto,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());
		
	}

	public void addotherFeesStamp() {
		StampFeesService stampFeesService = new StampFeesService(request, response);
		StampFeesDto stampFeesDto = new StampFeesDto();
		stampFeesDto.setStudentIds(request.getParameterValues("studentIDs"));
		stampFeesDto.setFeesTotalAmount(request.getParameter("feesTotalAmount"));
		stampFeesDto.setFeesCategoryIds(request.getParameterValues("feesIDS"));
		stampFeesDto.setFeesAmount(request.getParameterValues("fessFullCat"));
		stampFeesDto.setConcession(request.getParameterValues("feesConcession"));
		stampFeesDto.setTotalInstallments(request.getParameterValues("feesCount"));
		stampFeesDto.setFeesYears(request.getParameterValues("feesYears"));
		stampFeesService.addotherFeesStamp(stampFeesDto,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(),httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERID).toString());
		
	}

	public void otheradvanceSearch() {
		StampFeesService stampFeesService = new StampFeesService(request, response);
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		SearchStudentResponseDto searchStudentResponseDto = stampFeesService.otheradvanceSearch(searchStudentDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void advanceSearchForStampFees() {
		StampFeesService stampFeesService = new StampFeesService(request, response);
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		FeescategoryResponseDto feescategoryResponseDto = stampFeesService.advanceSearchForStampFees(searchStudentDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
		request.setAttribute("searchStudentList", feescategoryResponseDto.getSearchStudentList());
	}
}
