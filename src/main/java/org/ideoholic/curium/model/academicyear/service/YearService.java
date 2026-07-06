package org.ideoholic.curium.model.academicyear.service;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.stereotype.Service;

@Service
public class YearService {


	public ResultResponse saveYear(CurrentAcademicYearDto currentAcademicYearDto) {
		ResultResponse result = ResultResponse.builder().build();
		String errorService = null;
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		currentacademicyear
				.setCurrentacademicyear(DataUtil.emptyString(currentAcademicYearDto.getCurrentacademicyear()));
		currentacademicyear.setAcademicyearstartdate(
		        currentAcademicYearDto.getAcademicyearstartdate());
		currentacademicyear.setAcademicyearenddate(
		        currentAcademicYearDto.getAcademicyearenddate());
		currentacademicyear.setActive(
		        currentAcademicYearDto.getActive());
		currentacademicyear.setBranchid(
		        currentAcademicYearDto.getBranchid());
		currentacademicyear.setUserid(
		        currentAcademicYearDto.getUserid());
		errorService = new YearDAO().create(currentacademicyear);

		if (currentacademicyear != null) {
			result.setSuccess(true);

		}
		result.setMessage(errorService);
		return result;

	}

	public CurrentAcademicYearResponseDto updateYear(int branchId) {
		Currentacademicyear currentacademicyear = new Currentacademicyear();
		CurrentAcademicYearResponseDto result = null;

		currentacademicyear = new YearDAO().showYear(branchId);
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

	public Currentacademicyear getYear(int branchId) {
		try {
			return new YearDAO().showYear(branchId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
