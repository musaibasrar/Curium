package org.ideoholic.curium.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomMessageResponseException extends RuntimeException {
	private static final long serialVersionUID = 12345L;
	private final String errorMessage;

	public CustomMessageResponseException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public int getCode() {
		return 1000;
	}

	public String getMessage() {
		return errorMessage;
	}
}