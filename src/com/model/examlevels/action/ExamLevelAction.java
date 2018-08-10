package com.model.examlevels.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.examlevels.service.ExamLevelService;
import com.model.hr.dao.HrDAO;
import com.model.hr.dto.Payhead;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;

public class ExamLevelAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private static final Logger logger = LogManager.getLogger(ExamLevelAction.class);
	
	public ExamLevelAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action) {
            if (action.equalsIgnoreCase("examLevels")) {
                logger.info("Action is view examLevels");
                url = examLevels();
            } else if (action.equalsIgnoreCase("addExamLevels")) {
                logger.info("Action is addExamLevels");
                url = addExamLevels();
            } else if (action.equalsIgnoreCase("deleteMultipleExamLevels")) {
                logger.info("Action is deleteMultipleExamLevels");
                url = deleteMultipleExamLevels();
            }else if (action.equalsIgnoreCase("updateMultipleExamLevels")) {
                logger.info("Action is updateMultipleExamLevels");
                url = updateMultipleExamLevels();
            }else if (action.equalsIgnoreCase("getAdmissionNo")) {
                logger.info("Action is getAdmissionNo");
                getAdmissionNo();
            }
            return url;
	}

        private void getAdmissionNo() {
                try {
                    new ExamLevelService(request, response).getAdmissionNo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        private String updateMultipleExamLevels() {
            new ExamLevelService(request, response).updateMultipleExamLevels();
            return "Controller?process=LevelProcess&action=examLevels";
        }
    
        private String deleteMultipleExamLevels() {
            new ExamLevelService(request, response).deleteMultipleExamLevels();
            return "Controller?process=LevelProcess&action=examLevels";
        }
    
        private String addExamLevels() {
            new ExamLevelService(request, response).addExamLevels();
            return "Controller?process=LevelProcess&action=examLevels";
        }
    
        private String examLevels() {
            new ExamLevelService(request, response).examLevels();
            return "examleveldetails.jsp";
        }


}
