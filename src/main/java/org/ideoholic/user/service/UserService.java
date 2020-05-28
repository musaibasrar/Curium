package org.ideoholic.user.service;

public interface UserService {
	public String authenticateUser(String userName, String password);

	public String logout();
	
	public String changePassword(String currentPassword, String newPassword, String confirmNewPassword);
	
	public String dashBoard(String branchId,String toDate, String fromDate);
	
	public String feesdailysearch();
	
	public String feesmonthlysearch(String toDate, String fromDate);
	
	public String advanceSearch(SearchParameterDto searchParameter, String branchId);
	
	public String advanceSearchByParents(String branchId, String fathersName, String mothersName);
	
	public String backupData(String fileName);
	
	public String searchByDate(String branchId, String selectedbranchid, String toDate, String fromDate, String oneDay,
			 String dayOne,String dateFrom, String dateTo);

}
