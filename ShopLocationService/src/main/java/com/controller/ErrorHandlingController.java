package com.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.model.CustomException;
import com.model.ExceptionResponse;

@ControllerAdvice
public class ErrorHandlingController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> generalExceptionHandle(CustomException e) throws Exception{
		ExceptionResponse response = new ExceptionResponse();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setDescription(e.getMessage()); 
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> customExceptionHandle(CustomException e) throws Exception{
		ExceptionResponse response = new ExceptionResponse();
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.setDescription(e.getMessage()); 
		return new ResponseEntity<ExceptionResponse>(response,HttpStatus.BAD_REQUEST);
	}

}
