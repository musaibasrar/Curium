package com.model.academicyear.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.adminexpenses.dto.Adminexpenses;
import com.model.department.dao.departmentDAO;
import com.model.department.dto.Department;
import com.model.feescategory.dao.feesCategoryDAO;
import com.model.feescategory.dto.Feescategory;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.position.dao.positionDAO;
import com.model.position.dto.Position;
import com.model.std.dao.standardDetailsDAO;
import com.model.std.dto.Classsec;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;

public class YearService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	
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
		errorService=new YearDAO().create(currentacademicyear);
		
		if(currentacademicyear!=null){
			result=true;
			
		}
		httpSession.setAttribute("errorMessage", errorService);
            return result;
		
	}


	

	public boolean updateYear() {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		
		currentacademicyear = new YearDAO().showYear();
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
	        	Currentacademicyear currentYear = new YearDAO().showYear();
	            httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());

	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = false;
	        }
	        return result;
		
	}
	
	


	
}
