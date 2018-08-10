package com.model.qualification.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.examlevels.service.ExamLevelService;
import com.model.qualification.service.QualificationService;

public class QualificationAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private static final Logger logger = LogManager.getLogger(QualificationAction.class);
	
	public QualificationAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action) {
            if (action.equalsIgnoreCase("viewQualification")) {
                logger.info("Action is view Qualification");
                url = viewQualification();
            } else if (action.equalsIgnoreCase("addQualification")) {
                logger.info("Action is addQualification");
                url = addQualification();
            } else if (action.equalsIgnoreCase("deleteMultipleQualification")) {
                logger.info("Action is deleteMultipleQualification");
                url = deleteMultipleQualification();
            }else if (action.equalsIgnoreCase("updateMultipleQualification")) {
                logger.info("Action is updateMultipleQualification");
                url = updateMultipleQualification();
            }
            return url;
	}

        private String updateMultipleQualification() {
            new QualificationService(request, response).updateMultipleQualification();
            return "Controller?process=QualificationProcess&action=viewQualification";
        }
    
        private String deleteMultipleQualification() {
            new QualificationService(request, response).deleteMultipleQualification();
            return "Controller?process=QualificationProcess&action=viewQualification";
        }
    
        private String addQualification() {
            new QualificationService(request, response).addQualification();
            return "Controller?process=QualificationProcess&action=viewQualification";
        }
    
        private String viewQualification() {
            new QualificationService(request, response).viewQualification();
            return "qualification.jsp";
        }


}
