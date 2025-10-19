package org.ideoholic.curium.model.studentdiary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.diary.dto.DiaryDetailsMessageResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.model.studentdiary.dto.AddStudentDiaryDto;
import org.ideoholic.curium.model.studentdiary.service.StudentDiaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDiaryActionAdapter {
	
	    @Autowired
	    private HttpServletRequest request;

	    @Autowired
	    private HttpServletResponse response;

	    @Autowired
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	    private String USERLOGINID = "userloginid";
	    private String CURRENTACADEMICYEAR = "currentAcademicYear";

		public void addDiary() {
			StudentDiaryservice studentDiaryservice = new StudentDiaryservice(request, response);
			AddStudentDiaryDto addStudentDiaryDto = new AddStudentDiaryDto();
			addStudentDiaryDto.setStudentId(request.getParameter("studentId"));
			addStudentDiaryDto.setClassAndSec(request.getParameter("classandsec"));
			addStudentDiaryDto.setMessageBody(request.getParameter("messagebody"));
			addStudentDiaryDto.setSubject(request.getParameter("subject"));
			addStudentDiaryDto.setCreatedDate(request.getParameter("createddate"));
			studentDiaryservice.addDiary(addStudentDiaryDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(USERLOGINID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		}

		public void viewDiary() {
			StudentDiaryservice studentDiaryservice = new StudentDiaryservice(request, response);
			String page = request.getParameter("page");
			DiaryResponseDto diaryResponseDto = studentDiaryservice.viewDiary(page, httpSession.getAttribute(BRANCHID).toString());
			request.setAttribute("diary", diaryResponseDto.getDiaryDetails());
			request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
			request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());
		}

		public void viewDiaryParent() {
			StudentDiaryservice studentDiaryservice = new StudentDiaryservice(request, response);
			StudentIdPageDto studentIdPageDto = new StudentIdPageDto();
	        studentIdPageDto.setStudentId(request.getParameter("id"));
	        studentIdPageDto.setPage(request.getParameter("page"));
	        DiaryResponseDto diaryResponseDto = studentDiaryservice.viewDiaryParent(studentIdPageDto, httpSession.getAttribute(BRANCHID).toString());
	        request.setAttribute("studentdiaryparents", diaryResponseDto.getDiaryDetails());
	        request.setAttribute("noOfPages", diaryResponseDto.getNoOfPages());
	        request.setAttribute("currentPage", diaryResponseDto.getCurrentPage());
			
		}

		public void deleteRecord() {
			StudentDiaryservice studentDiaryservice = new StudentDiaryservice(request, response);
			DairyIdsDto dairyIdsDto = new DairyIdsDto();
	        dairyIdsDto.setIdDiary(request.getParameterValues("id"));
			studentDiaryservice.deleteRecord(dairyIdsDto);
			
		}

		public boolean viewDetailsOfDiaryMessage() {
			StudentDiaryservice studentDiaryservice = new StudentDiaryservice(request, response);
			StudentIdDto studentIdDto =new StudentIdDto();
	        studentIdDto.setStudentId(request.getParameter("id").toString());
	        DiaryDetailsMessageResponseDto viewDetailsOfDiaryMessageResponseDto = studentDiaryservice.viewDetailsOfDiaryMessage(studentIdDto);
			httpSession.setAttribute("studentdiary", viewDetailsOfDiaryMessageResponseDto.getStudentDiary());
		    return viewDetailsOfDiaryMessageResponseDto.isSuccess();
		}

	

}
