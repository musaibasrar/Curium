package com.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.academicyear.action.YearAction;
import com.model.account.action.AccountAction;
import com.model.adminexpenses.action.AdminAction;
import com.model.attendance.action.AttendanceAction;
import com.model.department.action.DepartmentAction;
import com.model.employee.action.EmployeeAction;
import com.model.examdetails.action.ExamDetailsAction;
import com.model.examlevels.action.ExamLevelAction;
import com.model.feescategory.action.FeesAction;
import com.model.feescollection.action.FeesCollectionAction;
import com.model.feesdetails.action.FeesDetailsAction;
import com.model.hr.action.HrAction;
import com.model.marksdetails.action.MarksDetailsAction;
import com.model.position.action.PositionAction;
import com.model.printids.action.PrintIdsAction;
import com.model.sendemail.action.EmailAction;
import com.model.sendsms.action.SmsAction;
import com.model.stampfees.action.StampFeesAction;
import com.model.student.action.StudentAction;
import com.model.subjectdetails.action.SubjectDetailsAction;
import com.model.user.action.UserAction;

/**
 * Servlet implementation class Controller
 */

public class AjaxController extends HttpServlet {
	
	
	
       
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        String process = request.getParameter("process");
        String action = request.getParameter("action");
        if (process.equalsIgnoreCase("SubGroupName")) {
        	new AccountAction(request, response).execute(action);
        }else if(process.equalsIgnoreCase("HrProcess")){
        	new HrAction(request, response).execute(action);
        }else if("LevelProcess".equalsIgnoreCase(process)){
            new ExamLevelAction(request, response).execute(action);;
        }else if("AttendanceProcess".equalsIgnoreCase(process)){
            new AttendanceAction(request, response).execute(action);;
        }
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
			}

	/***
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
}
