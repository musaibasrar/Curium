package org.ideoholic.curium.model.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.documents.dto.CharacterDto;
import org.ideoholic.curium.model.documents.dto.CharacterResponseDto;
import org.ideoholic.curium.model.documents.dto.ParentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.documents.dto.SearchStudentResponseDto;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.documents.dto.StudentNameSearchDto;
import org.ideoholic.curium.model.documents.dto.TcResponseDto;
import org.ideoholic.curium.model.documents.dto.TransferCertificateDto;
import org.ideoholic.curium.model.documents.dto.TransferCertificateResponseDto;
import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class DocumentActionAdapter {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private StandardActionAdapter standardActionAdapter;

	private String BRANCHID = "branchid";

	public boolean exportAdmissionAbstract() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		ResultResponse response = documentService.exportAdmissionAbstract(studentIdsDto,
				httpSession.getAttribute(BRANCHID).toString());
		return response.isSuccess();
	}

	public boolean searchForStudents() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
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
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		ParentListResponseDto studentListAaResponseDto = documentService
				.admissionAbstract(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("studentListaa", studentListAaResponseDto.getList());
		return studentListAaResponseDto.isSuccess();
	}

	public void multiClassSearchAdmissoinReport() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		StudentNameSearchDto studentNameSearchDto = new StudentNameSearchDto();
		studentNameSearchDto.setYearOfAdmission(request.getParameter("yearofadmission"));
		studentNameSearchDto.setNameSearch(request.getParameter("namesearch"));
		studentNameSearchDto.setClassSearch(request.getParameterValues("classsearch"));
		SearchStudentResponseDto searchStudentResponseDto = documentService
				.multiClassSearchAdmissoinReport(studentNameSearchDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public void multiClassSearchPendingAdmissoinReport() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		StudentNameSearchDto studentNameSearchDto = new StudentNameSearchDto();
		studentNameSearchDto.setNameSearch(request.getParameter("namesearch"));
		studentNameSearchDto.setClassSearch(request.getParameterValues("classsearch"));
		SearchStudentResponseDto searchStudentResponseDto = documentService.multiClassSearchPendingAdmissoinReport(
				studentNameSearchDto, httpSession.getAttribute(BRANCHID).toString(),
				httpSession.getAttribute("currentAcademicYear").toString());
		request.setAttribute("searchStudentList", searchStudentResponseDto.getSearchStudentList());
	}

	public String GenerateCharacterCertificate() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
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
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
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
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		int studentId = DataUtil.parseInt(request.getParameter("id"));
		TcResponseDto tcResponseDto = documentService.printTransferCertificate(studentId);
		request.setAttribute("studentdetails", tcResponseDto.getParents());
		request.setAttribute("tcdetails", tcResponseDto.getTc());
		return tcResponseDto.isSuccess();
	}

	public boolean transferCertificate() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		ResultResponse resultResponse = documentService.transferCertificate(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("studentListtc", resultResponse.getResultList());
		return resultResponse.isSuccess();
	}

	public String generateTransferCertificate() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		TransferCertificateDto transferCertificateDto = new TransferCertificateDto();
		transferCertificateDto.setStudentId(request.getParameter("studentId"));
		transferCertificateDto.setReason(request.getParameter("reason"));
		transferCertificateDto.setBookNo(request.getParameter("bookno"));
		transferCertificateDto.setTcNo(request.getParameter("tcno"));
		transferCertificateDto.setCaste(request.getParameter("caste"));
		transferCertificateDto.setClassInWord(request.getParameter("classinword"));
		transferCertificateDto.setLastExam(request.getParameter("lastexam"));
		transferCertificateDto.setFailPass(request.getParameter("failpass"));
		transferCertificateDto.setFirstSubject(request.getParameter("firstsubject"));
		transferCertificateDto.setSecondSubject(request.getParameter("secondsubject"));
		transferCertificateDto.setThirdSubject(request.getParameter("thirdsubject"));
		transferCertificateDto.setFourthSubject(request.getParameter("Fourthsubject"));
		transferCertificateDto.setFifthSubject(request.getParameter("Fifthsubject"));
		transferCertificateDto.setSixthSubject(request.getParameter("sixthsubject"));
		transferCertificateDto.setPinFig(request.getParameter("pinfig"));
		transferCertificateDto.setPinWord(request.getParameter("pinword"));
		transferCertificateDto.setDues(request.getParameter("dues"));
		transferCertificateDto.setConcession(request.getParameter("concession"));
		transferCertificateDto.setWorkingDays(request.getParameter("workingdays"));
		transferCertificateDto.setPresent(request.getParameter("present"));
		transferCertificateDto.setNcc(request.getParameter("ncc"));
		transferCertificateDto.setGame(request.getParameter("game"));
		transferCertificateDto.setConduct(request.getParameter("conduct"));
		transferCertificateDto.setDateCert(request.getParameter("datecert"));
		transferCertificateDto.setRemarks(request.getParameter("Remarks"));
		transferCertificateDto.setDateOfTc(request.getParameter("dateoftc"));
		TransferCertificateResponseDto transferCertificateResponseDto = documentService.generateTransferCertificate(transferCertificateDto);
		
		request.setAttribute("leavingReason", transferCertificateResponseDto.getReason());
		request.setAttribute("bookno", transferCertificateResponseDto.getBookNo());
		 request.setAttribute("tcno", transferCertificateResponseDto.getTcNo());
		request.setAttribute("caste", transferCertificateResponseDto.getCaste());
		request.setAttribute("classinword", transferCertificateResponseDto.getClassInWord());
		request.setAttribute("lastexam", transferCertificateResponseDto.getLastExam());
		request.setAttribute("failpass", transferCertificateResponseDto.getFailPass());
		request.setAttribute("firstsubject", transferCertificateResponseDto.getFirstSubject());
		request.setAttribute("secondsubject", transferCertificateResponseDto.getSecondSubject());
		request.setAttribute("thirdsubject", transferCertificateResponseDto.getThirdSubject());
		request.setAttribute("Fourthsubject",transferCertificateResponseDto.getFourthSubject());
		request.setAttribute("Fifthsubject", transferCertificateResponseDto.getFifthSubject());
		request.setAttribute("sixthsubject", transferCertificateResponseDto.getSixthSubject());
		request.setAttribute("pinfig", transferCertificateResponseDto.getPinFig());
		request.setAttribute("pinword", transferCertificateResponseDto.getPinWord());
		request.setAttribute("dues", transferCertificateResponseDto.getDues());
		request.setAttribute("concession",transferCertificateResponseDto.getConcession());
		request.setAttribute("workingdays",transferCertificateResponseDto.getWorkingDays());
		request.setAttribute("present", transferCertificateResponseDto.getPresent());
		request.setAttribute("ncc",transferCertificateResponseDto.getNcc());
		request.setAttribute("game", transferCertificateResponseDto.getGame());
		request.setAttribute("conduct", transferCertificateResponseDto.getConduct());
		request.setAttribute("datecert", transferCertificateResponseDto.getDateCert());
		request.setAttribute("Remarks", transferCertificateResponseDto.getRemarks());
	    request.setAttribute("studentdetails", transferCertificateResponseDto.getParents());
		request.setAttribute("tcdetails",transferCertificateResponseDto.getTc());
		request.setAttribute("dateinword",transferCertificateResponseDto.getDateInWord());
	    switch(transferCertificateResponseDto.getStatus())
	    {
	    case TCEXISTS:
	    	return "studentexists";
	    case TCNEW:
	    	return "true";
	    case TCFAILED:
	    	default:
	    	return "false";
	    }
	    
	}

	public void printCharacterCertificate() {
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		CharacterDto characterDto = new CharacterDto();
		characterDto.setCharacterStudent(request.getParameter("characterstudent"));
		CharacterResponseDto characterResponseDto = documentService.printCharacterCertificate(characterDto);
		request.setAttribute("character", characterResponseDto.getCharacter());
	}
	
	public String studentsArticleCertificate() {
		standardActionAdapter.viewClasses(); 
		return "studentarticlecertificate";
	}
	
	public String generateArticle() {

		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		ParentDto parentDto = documentService.GenerateCharacterCertificate(studentIdsDto);
		if (parentDto != null) {
			httpSession.setAttribute("studentdetailsbonafide", parentDto.getParents());
			return "articleprint";
		}
		return null;
	}
	
	public String studyCertificateforCounselling() {
		
		DocumentService documentService = new DocumentService(request, response, standardActionAdapter);
		StudentIdsDto studentIdsDto = new StudentIdsDto();
		studentIdsDto.setStudentIds(request.getParameterValues("studentIDs"));
		ParentDto parentDto = documentService.GenerateCharacterCertificate(studentIdsDto);
		if (parentDto != null) {
			httpSession.setAttribute("studentdetailsbonafide", parentDto.getParents());
			return "studycertificateforcounsellingprint";
		}
		return null;
	}
}
