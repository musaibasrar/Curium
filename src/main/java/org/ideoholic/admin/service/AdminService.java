package org.ideoholic.admin.service;

public interface AdminService {

	String viewAllExpenses(String branchId);

	String addExpenses(String branchId, String item, String quantity, String price, String entrydate);

	String deleteMultiple(String[] expensesIds);

}
