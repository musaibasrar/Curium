package org.ideoholic.curium.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.action.YearAction;
import org.ideoholic.curium.model.account.action.AccountAction;
import org.ideoholic.curium.model.adminexpenses.action.AdminAction;
import org.ideoholic.curium.model.attendance.action.AttendanceAction;
import org.ideoholic.curium.model.department.action.DepartmentAction;
import org.ideoholic.curium.model.documents.action.DocumentAction;
import org.ideoholic.curium.model.employee.action.EmployeeAction;
import org.ideoholic.curium.model.examdetails.action.ExamDetailsAction;
import org.ideoholic.curium.model.feescategory.action.FeesAction;
import org.ideoholic.curium.model.feescollection.action.FeesCollectionAction;
import org.ideoholic.curium.model.feesdetails.action.FeesDetailsAction;
import org.ideoholic.curium.model.hr.action.HrAction;
import org.ideoholic.curium.model.marksdetails.action.MarksDetailsAction;
import org.ideoholic.curium.model.mess.item.action.MessItemsAction;
import org.ideoholic.curium.model.mess.stockmove.action.MessStockMoveAction;
import org.ideoholic.curium.model.mess.supplier.action.MessSuppliersAction;
import org.ideoholic.curium.model.periods.action.PeriodAction;
import org.ideoholic.curium.model.position.action.PositionAction;
import org.ideoholic.curium.model.printids.action.PrintIdsAction;
import org.ideoholic.curium.model.sendemail.action.EmailAction;
import org.ideoholic.curium.model.sendsms.action.SmsAction;
import org.ideoholic.curium.model.stampfees.action.StampFeesAction;
import org.ideoholic.curium.model.std.action.StandardAction;
import org.ideoholic.curium.model.student.action.StudentAction;
import org.ideoholic.curium.model.subjectdetails.action.SubjectDetailsAction;
import org.ideoholic.curium.model.user.action.UserAction;

/**
 * Servlet implementation class Controller
 */

public class Controller extends HttpServlet {
       
	/**
	 * 
	 */

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
         nextUrl= new StandardAction(request, response).execute(action);
     }else if("test".equalsIgnoreCase(process)){
         nextUrl= "test.html";
     }else if("MessItemsProcess".equalsIgnoreCase(process)){
         nextUrl = new MessItemsAction(request, response).execute(action);
     }else if("MessSuppliersProcess".equalsIgnoreCase(process)){
         nextUrl = new MessSuppliersAction(request, response).execute(action);
     }else if("MessItemsMoveProcess".equalsIgnoreCase(process)){
         nextUrl = new MessStockMoveAction(request, response).execute(action);
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
