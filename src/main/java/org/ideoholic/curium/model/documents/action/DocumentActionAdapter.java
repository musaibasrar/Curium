package org.ideoholic.curium.model.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.dto.ParentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.model.documents.dto.StudentListAaResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentNameSearchDto;
import org.ideoholic.curium.model.documents.dto.TcResponseDto;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.util.DataUtil;
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
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		ResultResponse response = documentService.exportAdmissionAbstract(studentIdsDto,
				httpSession.getAttribute(BRANCHID).toString());
		return response.isSuccess();
	}

	public boolean searchForStudents() {
		DocumentService documentService = new DocumentService(request, response);
		SearchStudentDto searchStudentDto = new SearchStudentDto();
		searchStudentDto.setNameSearch(request.getParameter("namesearch"));
		searchStudentDto.setAdmNo(request.getParameter("admno"));
		searchStudentDto.setClassSearch(request.getParameter("classsearch"));
		searchStudentDto.setSecSearch(request.getParameter("secsearch"));
		SearchStudentResponseDto searchStudentResponseDto = documentService.searchForStudents(searchStudentDto,
				httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());

		return searchStudentResponseDto.isSuccess();
	}

	public boolean admissionAbstract() {
		DocumentService documentService = new DocumentService(request, response);
		StudentListAaResponseDto studentListAaResponseDto = documentService
				.admissionAbstract(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("studentListaa", studentListAaResponseDto.getList());
		return studentListAaResponseDto.isSuccess();
	}

	public void multiClassSearchAdmissoinReport() {
		DocumentService documentService = new DocumentService(request, response);
		StudentNameSearchDto studentNameSearchDto = new StudentNameSearchDto();
		studentNameSearchDto.setYearOfAdmission(request.getParameter("yearofadmission"));
		studentNameSearchDto.setNameSearch(request.getParameter("namesearch"));
		studentNameSearchDto.setClassSearch(request.getParameterValues("classsearch"));
		SearchStudentResponseDto searchStudentResponseDto = documentService
				.multiClassSearchAdmissoinReport(studentNameSearchDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void multiClassSearchPendingAdmissoinReport() {
		DocumentService documentService = new DocumentService(request, response);
		StudentNameSearchDto studentNameSearchDto = new StudentNameSearchDto();
		studentNameSearchDto.setNameSearch(request.getParameter("namesearch"));
		studentNameSearchDto.setClassSearch(request.getParameterValues("classsearch"));
		SearchStudentResponseDto searchStudentResponseDto = documentService.multiClassSearchPendingAdmissoinReport(
				studentNameSearchDto, httpSession.getAttribute(BRANCHID).toString(),
				httpSession.getAttribute("currentAcademicYear").toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public String GenerateCharacterCertificate() {
		DocumentService documentService = new DocumentService(request, response);
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		ParentDto parentDto = documentService.GenerateCharacterCertificate(studentIdsDto);
		if (parentDto != null) {
			httpSession.setAttribute("studentdetailsbonafide", parentDto.getParents());
			return "charactercertificateprint";
		}
		return null;
	}

	public String generateStudyCertificate() {
		DocumentService documentService = new DocumentService(request, response);
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		ParentDto parentDto = documentService.generateStudyCertificate(studentIdsDto);
		if (parentDto != null) {
			httpSession.setAttribute("studentdetailsbonafide", parentDto.getParents());
			return "studycertificateprint";
		}
		return null;
	}

	public boolean printTransferCertificate() {
		DocumentService documentService = new DocumentService(request, response);
		int studentId = DataUtil.parseInt(request.getParameter("id"));
		TcResponseDto tcResponseDto = documentService.printTransferCertificate(studentId);
		request.setAttribute("studentdetails", tcResponseDto.getParents());
		request.setAttribute("tcdetails", tcResponseDto.getTc());
		return tcResponseDto.isSuccess();
	}

	public boolean transferCertificate() {
		DocumentService documentService = new DocumentService(request, response);
		ResultResponse resultResponse = documentService.transferCertificate(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("studentListtc", resultResponse.getResultList());
		return resultResponse.isSuccess();
	}

}
