package com.microservices.exception;

import javax.servlet.http.HttpServletResponse;

public abstract class E000WalthRatingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public E000WalthRatingException(String string) {
		super(string);
	}
	

	public abstract int getErrorCode();
	
	public abstract String getErrorMessage();
	
	public boolean sendEmail() {
		return false;
	}
	
	public int getHttpResponseStatus()
	{
		return HttpServletResponse.SC_SERVICE_UNAVAILABLE; 
	}

	public String toString(){
		return " { \"error\":true,\"error_id\":"+this.getErrorCode()+",\"error_message\" : \"" +this.getErrorMessage()+"\"}";
	}
	
	
}

