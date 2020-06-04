package org.ideoholic.fees.service;

public interface FeesService {

	String viewFees(String branchId);

	String addFeesParticular(String branchId, String feescategory1, String fromclass, String toclass, String amount);

	String deleteMultiple(String[] idfeescategory);

	String deleteFeesCategory(String[] idfeescategory, String studentId);

}
