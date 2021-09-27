package com.model.stampfees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.academicyear.service.YearService;
import com.model.feescategory.service.FeesService;
import com.model.stampfees.service.StampFeesService;
import com.model.std.service.StandardService;
import com.model.student.service.StudentService;
import com.model.user.dao.UserDAO;
import com.model.user.dto.Login;
import com.model.user.service.UserService;

public class StampFeesAction {
	HttpServletRequest request;
    HttpServletResponse response;
    private String url;
	
	public StampFeesAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response=response;
	}

	public String execute(String action) {
	       if (action.equalsIgnoreCase("search")) {
	            url = search();
	        }else if (action.equalsIgnoreCase("showFeesDetails")) {
	            url = showFeesDetails();
	        }else if (action.equalsIgnoreCase("applyFees")) {
	            url = applyFees();
	        }else if (action.equalsIgnoreCase("delete")) {
	        	url = deleteFeesStructure();
	        }else if (action.equalsIgnoreCase("searchForFees")) {
	        	url = searchForFees();
	        }
	       return url;
	       
	}

	private String searchForFees() {
		new StampFeesService(request, response).advanceSearch();
        return "feesstructure.jsp";
	}

	private String deleteFeesStructure() {
		new StampFeesService(request, response).deleteFeesStamp();
        return "feesstampsuccess.jsp";
	}

	private String applyFees() {
		new StampFeesService(request, response).addFeesStamp();
        return "feesstampsuccess.jsp";
	}

	private String showFeesDetails() {
		new FeesService(request, response).viewFees();
		new YearService(request, response).getYear();
		new StandardService(request, response).viewClasses();
		return "stampfees.jsp";
	}

	private String search() {
		new StampFeesService(request, response).advanceSearch();
        return "stampfees.jsp";
	}

	

}
