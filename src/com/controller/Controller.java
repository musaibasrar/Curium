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
import com.model.adminexpenses.action.AdminAction;
import com.model.attendance.action.AttendanceAction;
import com.model.department.action.DepartmentAction;
import com.model.employee.action.EmployeeAction;
import com.model.examdetails.action.ExamDetailsAction;
import com.model.feescategory.action.FeesAction;
import com.model.feescollection.action.FeesCollectionAction;
import com.model.feesdetails.action.FeesDetailsAction;
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

public class Controller extends HttpServlet {
	
	
	
       
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        String process = request.getParameter("process");
        String action = request.getParameter("action");
        String first = request.getParameter("first");
        String page = request.getParameter("page");
        String criteria = request.getParameter("criteria");
       
        String nextUrl = "";
        System.out.println("Process "+process);
        if (process.equalsIgnoreCase("UserProcess")) {
            nextUrl = new UserAction(request, response).execute(action);
        }else if(process.equalsIgnoreCase("StudentProcess")){
            nextUrl= new StudentAction(request, response).execute(action,page);
        }else if(process.equalsIgnoreCase("FeesProcess")){
         nextUrl= new FeesAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("DepartmentProcess")){
         nextUrl= new DepartmentAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("PositionProcess")){
         nextUrl= new PositionAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("EmployeeProcess")){
         nextUrl= new EmployeeAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("FeesCollection")){
         nextUrl= new FeesCollectionAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("AdminProcess")){
         nextUrl= new AdminAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("SearchProcess")){
         nextUrl= new UserAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("YearProcess")){
         nextUrl= new YearAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("StampFeesProcess")){
         nextUrl= new StampFeesAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("ExamDetailsProcess")){
         nextUrl= new ExamDetailsAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("MarksDetailsProcess")){
         nextUrl= new MarksDetailsAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("SubjectDetailsProcess")){
         nextUrl= new SubjectDetailsAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("printids")){
         nextUrl= new PrintIdsAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("FeesDetails")){
         nextUrl= new FeesDetailsAction(request, response).execute(action,page);
     }else if(process.equalsIgnoreCase("SMSProcess")){
         nextUrl= new SmsAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("EmailProcess")){
         nextUrl= new EmailAction(request, response).execute(action);
     }else if(process.equalsIgnoreCase("AttendanceProcess")){
         nextUrl= new AttendanceAction(request, response).execute(action);
     }

        RequestDispatcher reg = request.getRequestDispatcher(nextUrl);
        response.toString();
        try {
            reg.forward(request, response);
            //reg.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
