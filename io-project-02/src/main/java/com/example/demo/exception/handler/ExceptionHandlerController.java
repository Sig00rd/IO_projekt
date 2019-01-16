package com.example.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.BadPitchRoleSpecifiedException;
import com.example.demo.exception.GameNotFoundException;
import com.example.demo.exception.UserNotSignedUpForGameException;
import com.example.demo.response.ResponseMessage;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(GameNotFoundException.class)
	public ResponseEntity<?> handleGameNotFound() {
		return new ResponseEntity<>(
				new ResponseMessage(
						"Failed! - Game of such id does not exists."),
				HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotSignedUpForGameException.class)
	public ResponseEntity<?> handleUserNotSignedUpForGame() {
		return new ResponseEntity<>(
				new ResponseMessage(
						"Failed! - You have not signed up for such game"),
				HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BadPitchRoleSpecifiedException.class)
	public ResponseEntity<?> handleBadPitchRoleSpecified() {
		return new ResponseEntity<>(new ResponseMessage(
				"Failed! - You have not signed for such game with such role"),
				HttpStatus.CONFLICT);
	}
}
