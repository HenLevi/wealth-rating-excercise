package com.microservices.exception;
public class E001PersonDoesntExistsException extends E000WalthRatingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public E001PersonDoesntExistsException(String str) {
		super(str);
	}

	@Override
	public int getErrorCode() {
		return 101;
	}

	@Override
	public String getErrorMessage() {
		return "Person Doesnt Exists";
	}
}







