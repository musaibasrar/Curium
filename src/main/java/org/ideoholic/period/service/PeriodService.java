package org.ideoholic.period.service;

import org.ideoholic.period.dto.SaveParamDto;

public interface PeriodService {

	String periodConfiguration(String branchId);

	String savePeriods(String branchId,SaveParamDto saveDto);

	String viewTimeTable(String periodMasterid);

	String deletePeriods(String[] periodMasterid1);

	String generateTimeTable(String branchId);

	String viewTeacherTimeTable(String branchId, String teacherName);

}
