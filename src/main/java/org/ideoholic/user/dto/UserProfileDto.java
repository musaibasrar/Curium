package org.ideoholic.user.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserProfileDto {
	private String userName;
	private String password;
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;
	private String branchId;
	private String toDate;
	private String fromDate;
	private String fathersName;
	private String mothersName;
	private String fileName;
	private String selectedbranchid;
	private String oneDay;
	private String dayOne;
	private String dateFrom;
	private String dateTo;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String firstName) {
		this.userName = firstName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String lastName) {
		this.password = lastName;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getBranchId() {
		return branchId;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSelectedbranchid() {
		return selectedbranchid;
	}

	public void setSelectedbranchid(String selectedbranchid) {
		this.selectedbranchid = selectedbranchid;
	}

	public String getOneDay() {
		return oneDay;
	}

	public void setOneDay(String oneDay) {
		this.oneDay = oneDay;
	}

	public String getDayOne() {
		return dayOne;
	}

	public void setDayOne(String dayOne) {
		this.dayOne = dayOne;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("userName: ").append(getUserName());
		sb.append(", password: ").append(getPassword());
		sb.append("}");
		return sb.toString();
	}
}