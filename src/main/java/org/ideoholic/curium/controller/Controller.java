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
import org.ideoholic.curium.model.importfile.action.ImportFileAction;
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
import org.ideoholic.curium.model.subjectdetails.action.SubjectDetailsAction;
import org.ideoholic.curium.model.student.action.StudentAction;

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

     if("test".equalsIgnoreCase(process)){
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
