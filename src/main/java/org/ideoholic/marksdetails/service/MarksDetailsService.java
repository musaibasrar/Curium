package org.ideoholic.marksdetails.service;

public interface MarksDetailsService {

	String addMarks(String branchId, String currentAcademicYear, String[] studentIds, String[] studentsMarks,
			        String exam, String subject);

	String Search(String branchId, String studentname, String addClass, String addSec);

	String getSubjectExams(String branchId);

	String updateMarks(String branchId, String currentAcademicYear, String[] studentIds, String[] studentsMarks,
			           String[] marksid, String exam, String subject);

	String deleteMultiple(String[] studentIds, String[] marksid);

	String generateReport(String currentAcademicYear, String[] studentIds);

	String getStudentGraph(String branchId, String currentAcademicYear, String[] studentIds, String[] examClass);

	String downloadReportCard(byte[] buffer);

}
