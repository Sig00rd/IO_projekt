package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(
			UserAlreadyExistsException exc) {

		UserErrorResponse errorResponse = new UserErrorResponse(
				HttpStatus.CONFLICT.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

}
