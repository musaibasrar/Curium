package org.ideoholic.curium.model.academicyear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ResultResponse;
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

	public boolean saveYear() {
		YearService yearService = new YearService(request, response);

		CurrentAcademicYearDto currentacademicyeardto = new CurrentAcademicYearDto();
		ResultResponse response = yearService.saveYear(currentacademicyeardto);
		if (response == null) {
			return false;
		}

		httpSession.setAttribute("errorMessage", response.getMessage());

		return response.isSuccess();
	}

}
