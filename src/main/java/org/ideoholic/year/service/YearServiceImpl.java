package org.ideoholic.year.service;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.util.DataUtil;

public class YearServiceImpl implements YearService {
	
	public String saveYear(String academicYear) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean result=false;
		String errorService=null;
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		errorService=new YearDAO().create(currentacademicyear);
		
		if(currentacademicyear!=null){
			result=true;
			
		}
		sb.append("errorMessage").append(errorService);
		sb.append("}");
            return sb.toString();
		
	}
	
	public String updateYear() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		
		currentacademicyear = new YearDAO().showYear();
			if(currentacademicyear != null){
				sb.append("currentyear").append(currentacademicyear.getCurrentacademicyear());
				sb.append("result:").append(true);
				return sb.toString();
			}else{
				sb.append("result:").append(false);
				sb.append("}");
				return sb.toString();
			}
			
	}

}
