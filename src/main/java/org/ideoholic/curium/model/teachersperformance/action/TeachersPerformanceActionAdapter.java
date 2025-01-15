package org.ideoholic.curium.model.teachersperformance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.studentdiary.dto.TeacherDetailResponseDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.teachersperformance.dto.TeacherDetailsDto;
import org.ideoholic.curium.model.teachersperformance.service.TeacherPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeachersPerformanceActionAdapter {
	
	 @Autowired
	 private HttpServletRequest request;
	 @Autowired
     private HttpServletResponse response;
	 @Autowired
     private HttpSession httpSession;
	 @Autowired
     private TeacherPerformanceService teacherPerformanceService;
     private String BRANCHID = "branchid";

	public void getDetailofteacher() {
		TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto();
		teacherDetailsDto.setClasssec(request.getParameterValues("classesselected"));
		teacherDetailsDto.setSubjectDetails(request.getParameter("subject"));
		teacherDetailsDto.setAcademicYear(request.getParameter("academicyear"));
		TeacherDetailResponseDto teacherDetailResponseDto = teacherPerformanceService.getDetailofteacher(teacherDetailsDto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("subjectaveragelist", teacherDetailResponseDto.getSubjectAverageList());
		request.setAttribute("subjectaveragelistsize", teacherDetailResponseDto.getSubjectAverageListSize());
		request.setAttribute("subjectName", teacherDetailResponseDto.getSubjectName());
		
	}
	
	public boolean readListOfSubjects() {
		
		SubjectsResponseDto result = teacherPerformanceService.readListOfSubjects(httpSession.getAttribute("branchid").toString());
		 httpSession.setAttribute("listSubject", result.getSubjects());
		 return result.isSuccess();

	}

}
