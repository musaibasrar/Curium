package org.ideoholic.examdetails.service;

import org.ideoholic.examdetails.dto.ExamDetailsParamDto;

public interface ExamDetailsService {

	String addExam(String branchId, String examName);

	String readListOfExams(String branchId);

	String deleteMultiple(String[] examIds);

	String addSchedule(String branchId, String[] subject, String[] date, String[] startTime, String[] endTime,
			String[] classesSelected, String academicYear, String exam);

	String deleteExamSchedule(String[] examIds);

	String printPreviewHallTicket(ExamDetailsParamDto examParamDto);

	

}
