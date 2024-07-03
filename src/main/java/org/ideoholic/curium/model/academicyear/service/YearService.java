package org.ideoholic.curium.model.academicyear.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.ResultResponse;

public class YearService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

	public ResultResponse saveYear(CurrentAcademicYearDto currentAcademicYearDto) {
		ResultResponse result = ResultResponse.builder().build();
		String errorService = null;
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		currentacademicyear
				.setCurrentacademicyear(DataUtil.emptyString(currentAcademicYearDto.getCurrentacademicyear()));

		errorService = new YearDAO().create(currentacademicyear);

		if (currentacademicyear != null) {
			result.setSuccess(true);

		}
		result.setMessage(errorService);
		return result;

	}

	public CurrentAcademicYearResponseDto updateYear() {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		CurrentAcademicYearResponseDto result = null;

		currentacademicyear = new YearDAO().showYear();
		if (currentacademicyear != null) {
			result = CurrentAcademicYearResponseDto.builder()
					.currentayid(currentacademicyear.getCayid())
					.currentacademicyear(currentacademicyear.getCurrentacademicyear())
					.success(true).build();
		} else {
			result = CurrentAcademicYearResponseDto.builder()
					.success(false).build();
		}
		return result;
	}

	public Currentacademicyear getYear() {
		try {
			return new YearDAO().showYear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
