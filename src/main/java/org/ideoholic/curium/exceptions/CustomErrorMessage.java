package org.ideoholic.curium.exceptions;

import lombok.Getter;

@Getter
public enum CustomErrorMessage {

	ERROR(100, "error"),
	EXPORTFAILURE(101, "exportfailure");

	private final int code;
	private final String message;

	CustomErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

}