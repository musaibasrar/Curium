package org.ideoholic.email.dto;

public class EmailProfileDto {
	
	private String branchId;
	private String addClass;
	private String addSec;
	private String subject;
	private String messageBody;
	
	
	
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
