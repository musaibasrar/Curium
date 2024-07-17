package org.ideoholic.curium.model.feescategory.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.dto.SearchStudentDto;
import org.ideoholic.curium.model.feescategory.dto.ConcessionDto;
import org.ideoholic.curium.model.feescategory.dto.FeescategoryResponseDto;
import org.ideoholic.curium.model.feescategory.dto.IdFeescategoryDto;
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

	public String applyConcession() {
		FeesService feesService = new FeesService(request, response);
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
		FeesService feesService = new FeesService(request, response);
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
		FeesService feesService = new FeesService(request, response);
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
		FeesService feesService = new FeesService(request, response);
		FeescategoryResponseDto feescategoryResponseDto = feesService.viewFees(httpSession.getAttribute("branchid").toString(),httpSession.getAttribute("currentAcademicYear").toString());
		httpSession.setAttribute("feescategory", feescategoryResponseDto.getFeescategory());
		return feescategoryResponseDto.isSuccess();
	}

	public String deleteFeesCategory() {
		FeesService feesService = new FeesService(request, response);
		ConcessionDto concessionDto = new ConcessionDto();
		concessionDto.setSfsid(request.getParameterValues("sfsid"));
		concessionDto.setId(request.getParameter("id"));
		StudentIdDto studentIdDto = feesService.deleteFeesCategory(concessionDto,httpSession.getAttribute("branchid").toString());
		String studentId  = studentIdDto.getStudentId();
		return studentId;
	}

	public void deleteMultiple() {
		FeesService feesService = new FeesService(request, response);
		IdFeescategoryDto idFeescategoryDto = new IdFeescategoryDto();
		idFeescategoryDto.setIdFeesCategory(request.getParameterValues("idfeescategory"));
		feesService.deleteMultiple(idFeescategoryDto);
		
	}

	public boolean viewAllStudentsList() {
		FeesService feesService = new FeesService(request, response);
		ParentListResponseDto parentListResponseDto = feesService.viewAllStudentsList(httpSession.getAttribute("branchid").toString());
		request.setAttribute("studentListFeesCollection", parentListResponseDto.getList());
		return parentListResponseDto.isSuccess();
	}

	public void viewAllBranchStudents() {
		FeesService feesService = new FeesService(request, response);
		StudentListResponseDto studentListResponseDto = feesService.viewAllBranchStudents();
		request.setAttribute("studentListFeesCollection", studentListResponseDto.getStudentListFeesCollection());
	}
	
}
