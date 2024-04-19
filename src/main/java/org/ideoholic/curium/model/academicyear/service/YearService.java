package org.ideoholic.curium.model.academicyear.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.dto.Yearmapper;
import org.ideoholic.curium.model.account.dto.AccountDto;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentMapper;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ResultResponse;

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

	public ResultResponse saveYear(CurrentAcademicYearDto currentAcademicYearDto) {
		 ResultResponse result = null;
		 String currentacademicyear = currentAcademicYearDto.getCurrentacademicyear();
		
		return result;	
	}

	/*
	 * public CurrentAcademicYearDto saveYear(CurrentAcademicYearDto current) {
	 * boolean result=false; String errorService=null; Currentacademicyear
	 * currentacademicyear = Yearmapper.INSTANCE.mapCurrentacademicyear(current);
	 * //Currentacademicyear currentacademicyear = new Currentacademicyear();
	 * //currentacademicyear.setCurrentacademicyear(DataUtil.emptyString(request.
	 * getParameter("academicyear"))); errorService=new
	 * YearDAO().create(currentacademicyear);
	 * 
	 * 
	 * return Yearmapper.INSTANCE.mapCurrentAcademicYearDto(currentacademicyear);
	 * 
	 * }
	 */

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
