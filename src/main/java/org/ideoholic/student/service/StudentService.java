package org.ideoholic.student.service;

public interface StudentService {
	
	public String viewAllStudents(String branchId);
	

	public String viewAllStudentsList(String branchId);


	public String viewAllStudentsParents(String branchId, String pageN);


	public String addStudent(String branchId);


	public String viewDetailsOfStudent(long id);


	public String updateStudent(String branchId);


	public String archiveMultiple(String studentIds);


	public String viewAllStudentsArchive();


	public String deleteMultiple(String studentIds);


	public String restoreMultiple(String studentIds);


	public String promoteMultiple(String studentIds, String classStudying);


	public String viewfeesStructurePerYear(long id, String academicYear);


	public String exportDataForStudents(String studentIds, String branchId);


	public String generateBonafide(String studentIds);


	public String downlaodFile();


	public String addNew(String branchId);


	public String viewAllStudentsSuperAdmin();
	

}
