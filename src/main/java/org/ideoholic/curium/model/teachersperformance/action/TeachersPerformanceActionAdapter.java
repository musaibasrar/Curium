package org.ideoholic.curium.model.teachersperformance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.studentdiary.dto.TeacherDetailResponseDto;
import org.ideoholic.curium.model.teachersperformance.dto.TeacherDetailsDto;
import org.ideoholic.curium.model.teachersperformance.service.TeacherPerformanceService;

public class TeachersPerformanceActionAdapter {
	
	 private HttpServletRequest request;
     private HttpServletResponse response;
     private HttpSession httpSession;
     private String BRANCHID = "branchid";
     private String academicyear = "academicyear";

	public void getDetailofteacher() {
		TeacherPerformanceService teacherPerformanceService = new TeacherPerformanceService(request, response);
		TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto();
		teacherDetailsDto.setClasssec(request.getParameterValues("classesselected"));
		teacherDetailsDto.setSubjectDetails(request.getParameter("subject"));
		teacherDetailsDto.setAcademicYear(request.getParameter("academicyear"));
		TeacherDetailResponseDto teacherDetailResponseDto = teacherPerformanceService.getDetailofteacher(teacherDetailsDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("subjectaveragelist", teacherDetailResponseDto.getSubjectaverageList());
		request.setAttribute("subjectaveragelistsize", teacherDetailResponseDto.getSubjectaveragelistsize());
		request.setAttribute("subjectName", teacherDetailResponseDto.getSubjectName());
		
	}

}
