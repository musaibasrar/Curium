package com.model.referencebooks.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.examlevels.service.ExamLevelService;
import com.model.qualification.service.QualificationService;
import com.model.referencebooks.service.ReferenceBooksService;

public class ReferenceBooksAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private static final Logger logger = LogManager.getLogger(ReferenceBooksAction.class);
	
	public ReferenceBooksAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action) {
            if (action.equalsIgnoreCase("viewReferenceBooks")) {
                logger.info("Action is view Reference Books");
                url = viewReferenceBooks();
            } else if (action.equalsIgnoreCase("addReferenceBooks")) {
                logger.info("Action is addReferenceBooks");
                url = addReferenceBooks();
            } else if (action.equalsIgnoreCase("deleteMultiple")) {
                logger.info("Action is deleteMultiple");
                url = deleteMultiple();
            }else if (action.equalsIgnoreCase("updateMultipleRecords")) {
                logger.info("Action is updateMultipleQualification");
                url = updateMultipleRecords();
            }
            return url;
	}

        private String addReferenceBooks() {
        new ReferenceBooksService(request, response).addReferenceBooks();
        return "Controller?process=ReferenceBooksProcess&action=viewReferenceBooks";
    }

        private String viewReferenceBooks() {
            new ReferenceBooksService(request, response).viewReferenceBooks();
        return "referencebooks.jsp";
    }

        private String updateMultipleRecords() {
            new ReferenceBooksService(request, response).updateMultipleRecords();
            return "Controller?process=ReferenceBooksProcess&action=viewReferenceBooks";
        }
    
        private String deleteMultiple() {
            new ReferenceBooksService(request, response).deleteMultiple();
            return "Controller?process=ReferenceBooksProcess&action=viewReferenceBooks";
        }
}
