package com.example.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException() {
		super();
	}

	public UserAlreadyExistsException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UserAlreadyExistsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserAlreadyExistsException(String arg0) {
		super(arg0);
	}

	public UserAlreadyExistsException(Throwable arg0) {
		super(arg0);
	}

}
