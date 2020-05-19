package org.ideoholic.user.service;

public interface UserService {
	public String authenticateUser(String userName, String password);

	public String logout();
	
	public String changePassword(String currentPassword, String newPassword, String confirmNewPassword);

}
