/**
 * 
 */
package com.model.academicyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.service.YearService;
import com.model.adminexpenses.service.AdminService;
import com.model.feescategory.service.FeesService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class YearAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public YearAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("saveYear")) {
			System.out.println("Action is viewAllExpenses");
			url = saveYear();
		} else if (action.equalsIgnoreCase("updateYear")) {
			System.out.println("Action is addExpenses");
			url = updateYear();
		}
		return url;
	}

	

	

	private String saveYear() { 
		
		if(new YearService(request, response).saveYear()){
			return "yearsaved.jsp";
		}else{
		return "error.jsp";
		}
		
    }

	private String updateYear() {
		 new YearService(request, response).updateYear();
	            return "academicyear.jsp";
	       
		
	}

	

}
