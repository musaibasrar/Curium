package org.ideoholic.curium.model.academicyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YearActionAdapter {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private YearService yearService;

	public boolean saveYear() {

		CurrentAcademicYearDto currentacademicyeardto = new CurrentAcademicYearDto();
		currentacademicyeardto.setCurrentacademicyear(request.getParameter("academicyear"));
		currentacademicyeardto.setAcademicyearstartdate(DateUtil.indiandateParser(request.getParameter("fromdate")));
		currentacademicyeardto.setAcademicyearenddate(DateUtil.indiandateParser(request.getParameter("todate")));
		currentacademicyeardto.setActive(Boolean.valueOf(request.getParameter("active")));
		currentacademicyeardto.setBranchid(Integer.valueOf(httpSession.getAttribute(Constants.BRANCHID).toString()));
		currentacademicyeardto.setUserid(Integer.valueOf(httpSession.getAttribute(Constants.USERID).toString()));
		ResultResponse response = yearService.saveYear(currentacademicyeardto);
		if (response == null) {
			return false;
		}

		httpSession.setAttribute("errorMessage", response.getMessage());

		return response.isSuccess();
	}

	public void updateYear() {
		CurrentAcademicYearResponseDto currentacademicyear = yearService.updateYear(Integer.valueOf(httpSession.getAttribute(Constants.BRANCHID).toString()));
		request.setAttribute("currentyear", currentacademicyear.getCurrentacademicyear());
	}
	
	public boolean getYear() {
		Currentacademicyear currentYear = yearService.getYear(Integer.valueOf(httpSession.getAttribute(Constants.BRANCHID).toString()));
		if(currentYear == null) {
			return false;
		}
		httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());
		return true;
	}

}
