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
import com.model.branch.action.BranchAction;
import com.model.department.action.DepartmentAction;
import com.model.documents.action.DocumentAction;
import com.model.employee.action.EmployeeAction;
import com.model.examdetails.action.ExamDetailsAction;
import com.model.examlevels.action.ExamLevelAction;
import com.model.feescategory.action.FeesAction;
import com.model.feescollection.action.FeesCollectionAction;
import com.model.feesdetails.action.FeesDetailsAction;
import com.model.hr.action.HrAction;
import com.model.language.action.LanguageAction;
import com.model.marksdetails.action.MarksDetailsAction;
import com.model.order.action.OrderAction;
import com.model.order.booksinfo.action.BooksInfoAction;
import com.model.periods.action.PeriodAction;
import com.model.position.action.PositionAction;
import com.model.printids.action.PrintIdsAction;
import com.model.qualification.action.QualificationAction;
import com.model.referencebooks.action.ReferenceBooksAction;
import com.model.sendemail.action.EmailAction;
import com.model.sendsms.action.SmsAction;
import com.model.stampfees.action.StampFeesAction;
import com.model.std.action.StandardAction;
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
        String page = request.getParameter("page");
       
        String nextUrl = "";
        if ("UserProcess".equalsIgnoreCase(process)) {
            nextUrl = new UserAction(request, response).execute(action);
        }else if("StudentProcess".equalsIgnoreCase(process)){
            nextUrl= new StudentAction(request, response).execute(action,page);
        }else if("FeesProcess".equalsIgnoreCase(process)){
         nextUrl= new FeesAction(request, response).execute(action,page);
     }else if("DepartmentProcess".equalsIgnoreCase(process)){
         nextUrl= new DepartmentAction(request, response).execute(action,page);
     }else if("PositionProcess".equalsIgnoreCase(process)){
         nextUrl= new PositionAction(request, response).execute(action,page);
     }else if("EmployeeProcess".equalsIgnoreCase(process)){
         nextUrl= new EmployeeAction(request, response).execute(action,page);
     }else if("FeesCollection".equalsIgnoreCase(process)){
         nextUrl= new FeesCollectionAction(request, response).execute(action,page);
     }else if("AdminProcess".equalsIgnoreCase(process)){
         nextUrl= new AdminAction(request, response).execute(action,page);
     }else if("YearProcess".equalsIgnoreCase(process)){
         nextUrl= new YearAction(request, response).execute(action);
     }else if("StampFeesProcess".equalsIgnoreCase(process)){
         nextUrl= new StampFeesAction(request, response).execute(action);
     }else if("ExamDetailsProcess".equalsIgnoreCase(process)){
         nextUrl= new ExamDetailsAction(request, response).execute(action);
     }else if("MarksDetailsProcess".equalsIgnoreCase(process)){
         nextUrl= new MarksDetailsAction(request, response).execute(action);
     }else if("SubjectDetailsProcess".equalsIgnoreCase(process)){
         nextUrl= new SubjectDetailsAction(request, response).execute(action);
     }else if("printids".equalsIgnoreCase(process)){
         nextUrl= new PrintIdsAction(request, response).execute(action,page);
     }else if("FeesDetails".equalsIgnoreCase(process)){
         nextUrl= new FeesDetailsAction(request, response).execute(action,page);
     }else if("SMSProcess".equalsIgnoreCase(process)){
         nextUrl= new SmsAction(request, response).execute(action);
     }else if("EmailProcess".equalsIgnoreCase(process)){
         nextUrl= new EmailAction(request, response).execute(action);
     }else if("AttendanceProcess".equalsIgnoreCase(process)){
         nextUrl= new AttendanceAction(request, response).execute(action);
     }else if("AccountProcess".equalsIgnoreCase(process)){
         nextUrl= new AccountAction(request, response).execute(action);
     }else if("DocumentsProcess".equalsIgnoreCase(process)){
         nextUrl= new DocumentAction(request, response).execute(action);
     }else if("PeriodProcess".equalsIgnoreCase(process)){
         nextUrl= new PeriodAction(request, response).execute(action);
     }else if("HrProcess".equalsIgnoreCase(process)){
         nextUrl= new HrAction(request, response).execute(action);
     }else if("ClassProcess".equalsIgnoreCase(process)){
         nextUrl= new StandardAction(request, response).execute(action);;
     }else if("BranchProcess".equalsIgnoreCase(process)){
         nextUrl= new BranchAction(request, response).execute(action);;
     }else if("LevelProcess".equalsIgnoreCase(process)){
         nextUrl= new ExamLevelAction(request, response).execute(action);;
     }else if("LanguageProcess".equalsIgnoreCase(process)){
         nextUrl= new LanguageAction(request, response).execute(action);;
     }else if("QualificationProcess".equalsIgnoreCase(process)){
         nextUrl= new QualificationAction(request, response).execute(action);;
     }else if("ReferenceBooksProcess".equalsIgnoreCase(process)){
         nextUrl= new ReferenceBooksAction(request, response).execute(action);;
     }else if("OrderProcess".equalsIgnoreCase(process)){
         nextUrl= new OrderAction(request, response).execute(action);;
     }else if("BooksInfoProcess".equalsIgnoreCase(process)){
         nextUrl= new BooksInfoAction(request, response).execute(action);;
     }else if("test".equalsIgnoreCase(process)){
         nextUrl= "test.html";
     }
        
        RequestDispatcher reg = request.getRequestDispatcher(nextUrl);
        response.toString();
        try {
            reg.forward(request, response);
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
		processRequest(request, response);
	}

	
}