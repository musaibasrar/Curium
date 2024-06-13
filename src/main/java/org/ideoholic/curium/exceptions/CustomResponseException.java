package org.ideoholic.curium.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomResponseException extends RuntimeException {
	private static final long serialVersionUID = 12345L;
	private CustomErrorMessage errorMessage;

	public CustomResponseException(CustomErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}

	public int getCode() {
		return errorMessage.getCode();
	}

	public String getMessage() {
		return errorMessage.getMessage();
	}
}