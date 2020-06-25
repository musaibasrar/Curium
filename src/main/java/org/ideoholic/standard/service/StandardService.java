package org.ideoholic.standard.service;

public interface StandardService {
	
	
	String viewClasses(String branchId);

	String createClass(String branchId, String classDetails, String section);

	String deleteClasses(String[] classIds, String branchId);

	String addClassHierarchy(String branchId, String lowerClass, String upperClass);

	String deleteClassHierarchy(String[] classIds, String branchId);

	String graduateMultiple(String[] studentIds);

	String droppedoutMultiple(String[] studentIds);

	String leftoutMultiple(String[] studentIds);

	String viewGraduated();

	String viewDropped();

	String viewleft();

	String restoreMultipleGraduate(String[] studentIds);

	String restoreMultipleDroppedout(String[] studentIds);

	String restoreMultipleLeftout(String[] studentIds);

	String searchByClass(String classofStd, String branchId);


}
