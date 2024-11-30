package org.ideoholic.curium.model.studentdiary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.studentdiary.dto.AddStudentDiaryDto;
import org.ideoholic.curium.model.studentdiary.service.StudentDiaryservice;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDiaryActionAdapter {
	
	    @Autowired
	    private HttpServletRequest request;

	    @Autowired
	    private HttpServletResponse response;

	    @Autowired
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";

		public void addDiary() {
			StudentDiaryservice studentDiaryservice = new StudentDiaryservice(request, response);
			AddStudentDiaryDto addStudentDiaryDto = new AddStudentDiaryDto();
			addStudentDiaryDto.setStudentId(request.getParameter("studentId"));
			addStudentDiaryDto.setClassAndSec(request.getParameter("classandsec"));
			addStudentDiaryDto.setMessageBody(request.getParameter("messagebody"));
			addStudentDiaryDto.setSubject(request.getParameter("subject"));
			addStudentDiaryDto.setCreatedDate(request.getParameter("createddate"));
			studentDiaryservice.addDiary(addStudentDiaryDto,httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute("userloginid").toString(),httpSession.getAttribute("currentAcademicYear").toString());
		}

	

}
