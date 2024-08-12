package org.ideoholic.curium.util;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSReport implements Serializable{
	
	String mobile;
	String message;
	String status;
	String senton;
	
	public SMSReport() {
	}

	public SMSReport(String mobile, String message, String status, String senton) {
		this.mobile = mobile;
		this.message = message;
		this.status = status;
		this.senton = senton;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSenton() {
		return senton;
	}

	public void setSenton(String senton) {
		this.senton = senton;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
