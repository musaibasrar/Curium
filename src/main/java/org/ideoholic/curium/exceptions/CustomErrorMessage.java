package org.ideoholic.curium.exceptions;

public enum CustomErrorMessage {

	EXPORTFAILURE(100, "exportfailure");

	private int code;
	private String message;

	private CustomErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}