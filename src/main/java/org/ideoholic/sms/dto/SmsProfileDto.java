package org.ideoholic.sms.dto;

public class SmsProfileDto {

	private String branchId;
	private String addClass;
	private String addSec;
	private String messageBody;
	private String numbers;
	private int resultSMS;
	private String department;
	private String messageBodyStaff;
	private String message;
	
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getAddClass() {
		return addClass;
	}
	public void setAddClass(String addClass) {
		this.addClass = addClass;
	}
	public String getAddSec() {
		return addSec;
	}
	public void setAddSec(String addSec) {
		this.addSec = addSec;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public int getResultSMS() {
		return resultSMS;
	}
	public void setResultSMS(int resultSMS) {
		this.resultSMS = resultSMS;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMessageBodyStaff() {
		return messageBodyStaff;
	}
	public void setMessageBodyStaff(String messageBodyStaff) {
		this.messageBodyStaff = messageBodyStaff;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
