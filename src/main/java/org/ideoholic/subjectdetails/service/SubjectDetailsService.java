package org.ideoholic.subjectdetails.service;

public interface SubjectDetailsService {

	String readListOfSubjects(String branchId);

	String addSubject(String branchId, String subjectName, String minMarks, String maxMarks, String examName,
			          String examclass);

	String deleteMultiple(String[] examIds);

	String readListOfSubjectNames(String branchId);

	String deleteMultipleSubjects(String[] examIds);

}
