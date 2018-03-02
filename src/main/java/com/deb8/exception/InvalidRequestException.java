package com.deb8.exception;

public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = -7271321026508351257L;

	public InvalidRequestException(ExceptionMessage message) {
		super(message.getMessage());
	}
}