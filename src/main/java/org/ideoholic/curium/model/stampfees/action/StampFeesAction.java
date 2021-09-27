package org.ideoholic.curium.model.stampfees.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.model.user.service.UserService;

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
