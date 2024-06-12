package org.ideoholic.curium.model.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentIdDto;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentActionAdapter {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	private String BRANCHID = "branchid";

	public boolean exportAdmissionAbstract() {
		DocumentService documentService = new DocumentService(request, response);
		StudentIdDto studentIdDto = new StudentIdDto();
		studentIdDto.setStudentIds(request.getParameterValues("studentIDs"));
		ResultResponse response = documentService.exportAdmissionAbstract(studentIdDto,httpSession.getAttribute(BRANCHID).toString());
		return response.isSuccess();
	}

	public boolean searchForStudents() {
		DocumentService documentService = new DocumentService(request, response);
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNamesearch(request.getParameter("namesearch"));
		searchStudentDto.setAdmno(request.getParameter("admno"));
		searchStudentDto.setClasssearch(request.getParameter("classsearch"));
		searchStudentDto.setSecsearch(request.getParameter("secsearch"));
		SearchStudentResponseDto searchStudentResponseDto = documentService.searchForStudents(searchStudentDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
		
		return searchStudentResponseDto.isSuccess();
	}
}
