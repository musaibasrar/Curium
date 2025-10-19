package org.ideoholic.curium.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.ideoholic.curium.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
	
	@ExceptionHandler(CustomResponseException.class)
	public ResponseEntity<ErrorResponse> handleCustomResponseExceptions(CustomResponseException e) {

		HttpStatus status = HttpStatus.BAD_REQUEST; // 400

		return new ResponseEntity<>(new ErrorResponse(status, e.getCode(), e.getMessage()), status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

		// converting the stack trace to String
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		// specifying the stack trace in case of 500s
		return new ResponseEntity<>(new ErrorResponse(status, e.getMessage(), stackTrace), status);
	}
}
