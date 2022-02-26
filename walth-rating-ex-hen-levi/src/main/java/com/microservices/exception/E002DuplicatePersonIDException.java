package com.microservices.exception;



public class E002DuplicatePersonIDException  extends E000WalthRatingException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public E002DuplicatePersonIDException(String str) {
		super(str);
	}

	@Override
	public int getErrorCode() {
		return 102;
	}

	@Override
	public String getErrorMessage() {
		return "Duplicate ID  [+"+super.getMessage()+"+]";
	}
}




