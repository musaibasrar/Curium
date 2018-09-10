package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.account.action.AccountAction;
import com.model.attendance.action.AttendanceAction;
import com.model.branch.action.BranchAction;
import com.model.examlevels.action.ExamLevelAction;
import com.model.hr.action.HrAction;

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
            new ExamLevelAction(request, response).execute(action);
        }else if("AttendanceProcess".equalsIgnoreCase(process)){
            new AttendanceAction(request, response).execute(action);
        }else if("AttendanceProcess".equalsIgnoreCase(process)){
            new AttendanceAction(request, response).execute(action);
        }else if("DistrictProcess".equalsIgnoreCase(process)){
            new BranchAction(request, response).execute(action);
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
