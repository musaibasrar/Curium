package com.model.feescategory.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.dto.Feescategory;
import com.model.feescategory.service.FeesService;
import com.model.student.service.StudentService;
import com.util.DataUtil;
import com.util.DateUtil;

public class FeesAction {
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public FeesAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		 if (action.equalsIgnoreCase("feesView")) {
			System.out.println("Action is fees view");
			url = viewFees();
		}else if (action.equalsIgnoreCase("addFeesParticular")) {
			System.out.println("Action is addFeesParticular");
			url = addFeesParticular();
		}else if (action.equalsIgnoreCase("feesCollect")) {
			System.out.println("Action is feesCollect");
			url = feesCollect();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			System.out.println("Action is deleteMultiple");
			url = deleteMultiple();
		}
		return url;
	}

	private String deleteMultiple() {
		new FeesService(request, response).deleteMultiple();
        return "Controller?process=FeesProcess&action=feesView";
	}

	private String feesCollect() {
		
	new FeesService(request, response).viewFees();
	new FeesService(request, response).viewAllStudentsList();
    return "feesCollection.jsp";
    
	}

	private String addFeesParticular() {
		
		
		new FeesService(request, response).addFeesParticular();
        System.out.println("IN action's addFeesParticular");
        return "Controller?process=FeesProcess&action=feesView";
		
	}

	private String viewFees() {
		new FeesService(request, response).viewFees();
        System.out.println("IN action's fees view");
        return "feesCategory.jsp";
	}

}
