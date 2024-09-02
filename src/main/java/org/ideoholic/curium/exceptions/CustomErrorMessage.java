package org.ideoholic.curium.exceptions;

import lombok.Getter;

@Getter
public enum CustomErrorMessage {

	ERROR(100, "error"),
	EXPORTFAILURE(101, "exportfailure"),
	FAILURELEFTOUT(102, "failureleftout"),
	FAILUREDROPPEDOUT(103, "failuredroppedout"),
	FAILUREGRADUATE(104, "failuregraduate"),
 	NOTSAVEDEXPENSES(105,"notSavedExpenses"),
	NOTSAVED(106, "notSaved"),
	ERRORADDINGMARKS(107, "erroraddingmarks"),
	VIEWALL(108, "viewAll"),
	EMPLOYEENOTSAVED(109, "EmployeenotSaved");

	private final int code;
	private final String message;

	CustomErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

}