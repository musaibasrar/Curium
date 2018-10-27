/**
 * 
 */
package com.model.examdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.academicyear.service.YearService;
import com.model.branch.dao.BranchDAO;
import com.model.branch.service.BranchService;
import com.model.examdetails.service.ExamDetailsService;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.StudentService;
import com.model.subjectdetails.service.SubjectDetailsService;
import com.util.DataUtil;

/**
 * @author Musaib_2
 *
 */
public class ExamDetailsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	String url;
	String error = "error.jsp";
	
	public ExamDetailsAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String execute(String action) {
		if ("addExam".equalsIgnoreCase(action)) {
			url = addExam();
		}else if ("readListOfExams".equalsIgnoreCase(action)) {
			url = readListOfExams();
		}else if ("deleteMultiple".equalsIgnoreCase(action)) {
			url = deleteMultiple();
		}else if ("examSchedule".equalsIgnoreCase(action)) {
			url = examSchedule();
		}else if ("addSchedule".equalsIgnoreCase(action)) {
			url = addSchedule();
		}else if ("deleteExamSchedule".equalsIgnoreCase(action)) {
			url = deleteExamSchedule();
		}else if ("generateHallTicket".equalsIgnoreCase(action)) {
			url = generateHallTicket();
		}else if ("generateHallTicketCenter".equalsIgnoreCase(action)) {
                    url = generateHallTicketCenter();
                }else if ("searchHallTicketDetails".equalsIgnoreCase(action)) {
			url = searchHallTicketDetails();
		}else if ("printPreviewHallTicket".equalsIgnoreCase(action)) {
			url = printPreviewHallTicket();
		}
		return url;
	}

		
	private String generateHallTicketCenter() {
            
            boolean result;
            
                new ExamLevelService(request, response).examLevels();
                new BranchService(request, response).viewBranchesCenter();
                
            result = new SubjectDetailsService(request, response).readListOfSubjects();
            if (!result) 
                    return error;
            result = new YearService(request, response).getYear();
            if (!result) 
                    return error;
            result = new StudentService(request, response).viewAllStudentsListCenter();
            if (!result) 
                    return error;
            
            return "generatehallticket.jsp";
    }

    private String printPreviewHallTicket() {
		
		new ExamDetailsService(request, response).printPreviewHallTicket();
		return "printpreviewhallticket.jsp";
	}

	private String searchHallTicketDetails() {
		
		new ExamDetailsService(request, response).getExamScheduleDetails();
		//new ExamDetailsService(request, response).readListOfExams();
		//new SubjectDetailsService(request, response).readListOfSubjects();
		
		return "generatehallticket.jsp";
	}

	private String generateHallTicket() {
		
		boolean result;
		
		    new ExamLevelService(request, response).examLevels();
	            new BranchService(request, response).viewBranches();
	            
		/*result = new SubjectDetailsService(request, response).readListOfSubjects();
		if (!result) 
			return error;*/
		result = new YearService(request, response).getYear();
		if (!result) 
			return error;
		/*result = new StudentService(request, response).viewAllStudentsList();
		if (!result) 
			return error;*/
		
		return "generatehallticket.jsp";
	}

	private String deleteExamSchedule() {
		
		if(new ExamDetailsService(request, response).deleteExamSchedule()){
			return "Controller?process=ExamDetailsProcess&action=examSchedule";
		}else{
			return "error.jsp";
		}
	}

	private String addSchedule() {
		
		if(new ExamDetailsService(request, response).addSchedule()){
			return "Controller?process=ExamDetailsProcess&action=examSchedule";
		}else{
			return "error.jsp";
		}
	}

	private String examSchedule() {
		
		boolean result;
		
		new ExamLevelService(request, response).examLevels();
		new BranchService(request, response).viewBranches();
		result = new SubjectDetailsService(request, response).readListOfSubjects();
		if (!result) 
			return error;
		result = new YearService(request, response).getYear();
		if (!result) 
			return error;
		result = new ExamDetailsService(request, response).getExamSchedule();
		if (!result) 
			return error;
		
		return "examschedule.jsp";
		
	}

	private String deleteMultiple() {
		if(new ExamDetailsService(request, response).deleteMultiple()){
			return "Controller?process=ExamDetailsProcess&action=readListOfExams";
		}else{
			return "error.jsp";
		}
	}

	private String readListOfExams() {
		if(new ExamDetailsService(request, response).readListOfExams()){
			return "ExamDetails.jsp";
		}else{
			return "error.jsp";
		}
	}

	private String addExam() {
		
		if(new ExamDetailsService(request, response).addExam()){
			return "Controller?process=ExamDetailsProcess&action=readListOfExams";
		}else{
			return "error.jsp";
		}
        
	}

}
