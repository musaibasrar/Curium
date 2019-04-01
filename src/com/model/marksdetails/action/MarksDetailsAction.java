package com.model.marksdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.branch.service.BranchService;
import com.model.examdetails.service.ExamDetailsService;
import com.model.examlevels.service.ExamLevelService;
import com.model.language.service.LanguageService;
import com.model.marksdetails.service.MarksDetailsService;
import com.model.student.service.StudentService;

public class MarksDetailsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	String url;
	
	public MarksDetailsAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String execute(String action) {
		if (action.equalsIgnoreCase("addMarks")) {
			url = addMarks();
		}else if (action.equalsIgnoreCase("search")) {
			url = search();
		}else if (action.equalsIgnoreCase("viewMarks")) {
			url = viewMarks();
		}else if (action.equalsIgnoreCase("getSubjectsExams")) {
			url = getSubjectsExams();
		}else if (action.equalsIgnoreCase("updateMarks")) {
			url = updateMarks();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			url = deleteMultiple();
		}else if (action.equalsIgnoreCase("generateReport")) {
			url = generateReport();
		}else if (action.equalsIgnoreCase("searchForReport")) {
			url = searchForReport();
		}else if (action.equalsIgnoreCase("enterMarks")) {
            url = enterMarks();
        }else if (action.equalsIgnoreCase("deleteMarks")) {
            url = deleteMarks();
        }else if(action.equalsIgnoreCase("deleteMarksEntry")) {
        	url = deleteMarksEntry();
        }
		return url;
	}

	

	private String deleteMarksEntry() {
		
			String result = new MarksDetailsService(request, response).viewMarks();
			
			if(("MARKSENTRY").equalsIgnoreCase(result)) {
				return search();
			}else if(("VIEWMARKS").equalsIgnoreCase(result)) {
				return "marksdelete.jsp";
			}else {
				return "error.jsp";
			}
			
	}

	private String deleteMarks() {
		new MarksDetailsService(request, response).getSubjectExams();
        return "marksdelete.jsp";
	}

	private String enterMarks() {
	        new MarksDetailsService(request, response).enterMarks();
	        return "marksentry.jsp";
    }

    private String searchForReport() {
		new MarksDetailsService(request, response).Search();
        return "progressreport.jsp";
	}

	private String generateReport() {
		if (new MarksDetailsService(request, response).generateReport()) {
            return "reportcardsaved.jsp";
        } else {
            return "error.jsp";
        }
	}

	private String deleteMultiple() {
		if (new MarksDetailsService(request, response).deleteMultiple()) {
            return "markssaved.jsp";
        } else {
            return "notSaved.jsp";
        }
	}

	private String updateMarks() {
		if (new MarksDetailsService(request, response).updateMarks()) {
            return "markssaved.jsp";
        } else {
            return "error.jsp";
        }
	}

	private String getSubjectsExams() {
		new MarksDetailsService(request, response).getSubjectExams();
        return "markssearch.jsp";
	}

	private String viewMarks() {
		
		String result = new MarksDetailsService(request, response).viewMarks();
		
		if(("MARKSENTRY").equalsIgnoreCase(result)) {
			return search();
		}else if(("VIEWMARKS").equalsIgnoreCase(result)) {
			return "markssearch.jsp";
		}else {
			return "error.jsp";
		}
		
	}

	private String addMarks() {
		String result = new MarksDetailsService(request, response).addMarks();
		if (result=="true") {
            return "markssaved.jsp";
        } else if(result=="Duplicate") {
            return "erroraddingmarks.jsp";
        }else{
        	return "error.jsp";
        }
        	
	}
	
	private String search() {
		new MarksDetailsService(request, response).SearchMarksEntry();
        return "marksentry.jsp";
	}
	
	
}
