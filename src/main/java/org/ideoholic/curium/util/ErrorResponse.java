package org.ideoholic.curium.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
	// customizing timestamp serialization format
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp = new Date();

	private int code;

	private int customCode;

	private String status;

	private String message;

	private String stackTrace;

	public ErrorResponse() {
		timestamp = new Date();
	}

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this.code = httpStatus.value();
		this.status = httpStatus.name();
		this.message = message;
	}
	
	public ErrorResponse(HttpStatus httpStatus, int customCode, String message) {
		this(httpStatus, message);
		this.customCode = customCode;
	}


	public ErrorResponse(HttpStatus httpStatus, String message, String stackTrace) {
		this(httpStatus, message);

		this.stackTrace = stackTrace;
	}
}