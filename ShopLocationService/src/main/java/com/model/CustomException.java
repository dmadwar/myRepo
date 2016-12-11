package com.model;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception {

	private static final long serialVersionUID = 5781367781015903361L;

	private int code;

	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public CustomException(String message,int code){
		super(message);
		this.code=code;
	}
	
	public CustomException(Throwable cause){
		super(cause);
	}
	public CustomException(String message){
		super(message);
		this.message=message;
	}

}
