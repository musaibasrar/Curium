package org.ideoholic.curium.model.printids.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.employee.dto.PrintMultipleEmployeesResponseDto;
import org.ideoholic.curium.model.printids.dto.ParentCardResponsDto;
import org.ideoholic.curium.model.printids.dto.PrintIdsDto;
import org.ideoholic.curium.model.printids.service.PrintIdsService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrintIdsActionAdapter {
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private PrintIdsService printIdsService;

	public void updateCardValidity() {
		PrintIdsDto printIdsDto = new PrintIdsDto();
		printIdsDto.setStudentIDs(request.getParameterValues("studentIDs"));
		Map<String, String> allRequestParameters = new HashMap<>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String fieldName = enumeration.nextElement();
			String fieldValue = request.getParameter(fieldName);
			allRequestParameters.put(fieldName, fieldValue);
		}
		printIdsDto.setRequestParams(allRequestParameters);
		ResultResponse result = printIdsService.updateCardValidity(printIdsDto);
		request.setAttribute("updatecard", result.isSuccess());
	}

	public void searchDetailsCardValidity() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		ParentCardResponsDto parentCardResponsDto = printIdsService.searchDetailsCardValidity(searchStudentDto,httpSession.getAttribute("branchid").toString());
		request.setAttribute("parentscardlist", parentCardResponsDto.getParentsCardList());
	}

	public void searchDetails() {
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		SearchStudentResponseDto searchStudentResponseDto = printIdsService.searchDetails(searchStudentDto,httpSession.getAttribute("branchid").toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void printMultiple() {
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		PrintMultipleEmployeesResponseDto printMultipleEmployeesResponseDto = printIdsService.printMultiple(studentIdsDto,httpSession.getAttribute("currentAcademicYear").toString());
	    if(printMultipleEmployeesResponseDto.isSuccess()) {
	    	  httpSession.setAttribute("iInitial", printMultipleEmployeesResponseDto.getInitialValue());
	    	  httpSession.setAttribute("endValue", printMultipleEmployeesResponseDto.getEndValue());
	    	  for (Map.Entry<String, String> entry : printMultipleEmployeesResponseDto.getResultParams().entrySet()) {
	                httpSession.setAttribute(entry.getKey(), entry.getValue());
	            }
	    }
	}

}
