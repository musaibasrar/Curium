package org.ideoholic.curium.exceptions;

import lombok.Getter;

@Getter
public enum CustomErrorMessage {

	ERROR(100, "error"),
	EXPORTFAILURE(101, "exportfailure"),
	FAILURELEFTOUT(102, "failureleftout"),
	FAILUREDROPPEDOUT(103, "failuredroppedout"),
	FAILUREGRADUATE(104, "failuregraduate"),
 	NOTSAVEDEXPENSES(105,"notSavedExpenses");

	private final int code;
	private final String message;

	CustomErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

}