package com.model.academicyear.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.util.DataUtil;

public class YearService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	
	public YearService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}


	public boolean saveYear() {
		boolean result=false;
		String errorService=null;
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		currentacademicyear.setCurrentacademicyear(DataUtil.emptyString(request.getParameter("academicyear")));
		currentacademicyear.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		errorService=new YearDAO().create(currentacademicyear);
		
		if(currentacademicyear!=null){
			result=true;
			
		}
		httpSession.setAttribute("errorMessage", errorService);
            return result;
		
	}


	

	public boolean updateYear() {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		
		currentacademicyear = new YearDAO().showYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			if(currentacademicyear != null){
				request.setAttribute("currentyear", currentacademicyear.getCurrentacademicyear());
				return true;
			}else{
				return false;
			}
		
	}


	public boolean getYear() {
		 boolean result = false;
	        try {
	        	Currentacademicyear currentYear = new YearDAO().showYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
	            httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());

	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = false;
	        }
	        return result;
		
	}
	
	


	
}
