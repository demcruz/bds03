 package com.devsuperior.bds03.controllers.excepitions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
//	@ExceptionHandler(ResourcesNotFoundException.class)
//	public ResponseEntity<StandardError>entityNotFound(ResourcesNotFoundException e, HttpServletRequest request) {
//		StandardError err = new StandardError();
//		err.setTimestamp(Instant.now());
//		err.setStatus(HttpStatus.NOT_FOUND.value());
//		err.setError("não encontrado");
//		err.setMessage(e.getMessage());
//		err.setPath(request.getRequestURI());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
//		
//	}
//	
//	
//	@ExceptionHandler(DatabaseException.class)
//	public ResponseEntity<StandardError>database(DatabaseException e, HttpServletRequest request) {
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		StandardError err = new StandardError();
//		err.setTimestamp(Instant.now());
//		err.setStatus(status.value());
//		err.setError("Database exception");
//		err.setMessage(e.getMessage());
//		err.setPath(request.getRequestURI());
//		return ResponseEntity.status(status).body(err);
//		
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError>validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		
		return ResponseEntity.status(status).body(err);
		
	}

}
