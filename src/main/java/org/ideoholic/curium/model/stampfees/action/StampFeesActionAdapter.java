package org.ideoholic.curium.model.stampfees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
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
	
	@Autowired
	private StampFeesService stampFeesService;
	
	private String BRANCHID = "branchid";

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
}
