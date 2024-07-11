package org.ideoholic.curium.model.academicyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.service.YearService;
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
		ResultResponse response = yearService.saveYear(currentacademicyeardto);
		if (response == null) {
			return false;
		}

		httpSession.setAttribute("errorMessage", response.getMessage());

		return response.isSuccess();
	}

	public void updateYear() {
		CurrentAcademicYearResponseDto currentacademicyear = yearService.updateYear();
		request.setAttribute("currentyear", currentacademicyear.getCurrentacademicyear());
	}
	
	public boolean getYear() {
		Currentacademicyear currentYear = yearService.getYear();
		if(currentYear == null) {
			return false;
		}
		httpSession.setAttribute("currentYear", currentYear.getCurrentacademicyear());
		return true;
	}

}
