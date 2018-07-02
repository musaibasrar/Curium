package com.model.feesdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.service.FeesService;
import com.model.feesdetails.service.FeesDetailsService;
import com.model.student.service.StudentService;

public class FeesDetailsAction {
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public FeesDetailsAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		 if (action.equalsIgnoreCase("feesView")) {
			url = viewFees();
		}else if (action.equalsIgnoreCase("addFeesParticular")) {
			url = addFeesParticular();
		}else if (action.equalsIgnoreCase("feesCollect")) {
			url = feesCollect();
		}else if (action.equalsIgnoreCase("exportDataForFees")) {
			url = exportFeesData();
		}else if (action.equalsIgnoreCase("download")) {
			url = downloadFile();
		}
		return url;
	}

	private String downloadFile() {
		if(new FeesService(request, response).downlaodFile()){
			return "feesexportsuccess.jsp";
		}
        return "exportfailure.jsp";
	}

	private String feesCollect() {
		
	new FeesService(request, response).viewFees();    
    new StudentService(request, response).viewAllStudentsList();
    return "feesCollection.jsp";
    
	}

	private String addFeesParticular() {
		
		
		new FeesService(request, response).addFeesParticular();
        return "Controller?process=FeesProcess&action=feesView";
		
	}

	private String viewFees() {
		new FeesService(request, response).viewFees();
        return "feesCategory.jsp";
	}

	private String exportFeesData() {
		
		if(new FeesDetailsService(request, response).exportDataForFees()){
			return "feesexportsuccess.jsp";
		}else{
			return "exportfailure.jsp";
		}
		
	}
	
}
