/**
 * 
 */
package com.model.query.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.appointment.service.AppointmentService;
import com.model.department.service.DepartmentService;
import com.model.query.service.QueryService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class QueryAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public QueryAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {
		
		if (action.equalsIgnoreCase("addQuery")) {
			url = addQuery();
		}else if (action.equalsIgnoreCase("viewAllQueries")) {
			url = viewAllQueries();
		}else if (action.equalsIgnoreCase("completeQueries")) {
			url = completeQueries();
		}else if (action.equalsIgnoreCase("cancelQueries")) {
			url = cancelQueries();
		}else if (action.equalsIgnoreCase("inProgressQueries")) {
			url = inProgressQueries();
		}else if (action.equalsIgnoreCase("viewQueryDetails")) {
				viewQueryDetails();
		}else if (action.equalsIgnoreCase("updateQueries")) {
			url =  updateQueries();
		}else if (action.equalsIgnoreCase("viewAllQueriesDepartmentWise")) {
			url = viewAllQueriesDepartmentWise();
		}else if (action.equalsIgnoreCase("queryReport")) {
			url = queryReport();
		}else if (action.equalsIgnoreCase("generateQueriesReport")) {
			url = generateQueriesReport();
		}else if (action.equalsIgnoreCase("printQueriesReport")) {
			url = printQueriesReport();
		}else if (action.equalsIgnoreCase("feedback")) {
			url = feedback();
		}else if (action.equalsIgnoreCase("download")) {
			url = download();
		}else if (action.equalsIgnoreCase("exportQueriesReport")) {
			url = exportQueriesReport();
		}
		return url;
	}
	
	
	private String download() {
		if(new QueryService(request, response).download()) {
			return "exportsuccessquery.jsp";
		}
		return "exportfailure.jsp";
	}

	private String exportQueriesReport() {
		new QueryService(request, response).exportQueriesReport();
		return "exportsuccessquery.jsp";
	}

	private String feedback() {
		
		if(new QueryService(request, response).feedback()) {
			return "feedbackthankyou.jsp";	
		}else {
		return "feedbackthankyoufail.jsp";
		}
	}

	private String printQueriesReport() {
		return "printqueriesreport.jsp";
	}

	private String generateQueriesReport() {
		new QueryService(request, response).generateQueriesReport();
		return queryReport();
	}

	private String queryReport() {
		new DepartmentService(request, response).viewDepartment();
		new StudentService(request, response).viewAllStudentsList();
		return "queriesreport.jsp";
	}

	private String viewAllQueriesDepartmentWise() {
		
		if(new QueryService(request, response).viewAllQueriesDepartmentWise()){
			return "queries.jsp";
		}else{
			return "error.jsp";
		}
	}

	private String updateQueries() {
		new QueryService(request, response).updateQueries();
		return viewAllQueries();
	}

	private void viewQueryDetails() {
		
		try {
			new QueryService(request, response).viewQueryDetails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String inProgressQueries() {
		new QueryService(request, response).inProgressQueries();
		return viewAllQueries();
	}

	private String cancelQueries() {
		new QueryService(request, response).cancelQueries();
		return viewAllQueries();
	}

	private String completeQueries() {
		new QueryService(request, response).completeQueries();
		return viewAllQueries();
	}

	private String viewAllQueries() {
		
		boolean result = false;
		
		 if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("admin")) {
			 result = new QueryService(request, response).viewAllQueries(); 
         }else if(httpSession.getAttribute("userType").toString().equalsIgnoreCase("queries")) {
        	 result = new QueryService(request, response).viewAllQueriesDepartmentWise(); 
         }else {
        	 result = new QueryService(request, response).viewAllQueries(); 
         }
		 
		 if(result){
  			return "queries.jsp";
  		}else{
  			return "error.jsp";
  		}
		 
		 
	}

	private String addQuery() {
		
		if(new QueryService(request, response).addQuery()){
			return "querysuccess.jsp";
		}else{
			return "error.jsp";
		}
	}
}
