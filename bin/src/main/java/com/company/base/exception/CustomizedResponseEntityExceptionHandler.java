package com.company.base.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
	
	@ExceptionHandler(ItemNotFound.class)
	public final ResponseEntity<ErrorDetailsBase> handleItemNaoEncontrado(ItemNotFound ex, WebRequest request) {
		ErrorDetailsBase errorDetails = ErrorDetailsGeneral.builder().timestamp(new Date())
												.statusCode(HttpStatus.NOT_FOUND.value())
												.message(ex.getMessage())
												.details(request.getDescription(false))
												.build();
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidOperationException.class)
	public final ResponseEntity<ErrorDetailsBase> handleInvalidOperationException(InvalidOperationException ex, WebRequest request) {
		
		ErrorDetailsBase errorDetails = ErrorDetailsGeneral.builder().timestamp(new Date())
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.message(ex.getMessage())
				.details(request.getDescription(false))
				.build();
				
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorDetailsBase> handleInvalidOperatMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		ErrorDetailsBase beanValidationErrorDetails = BeanValidationErrorDetails.builder()
																	.timestamp(new Date())
																	.statusCode(HttpStatus.BAD_REQUEST.value())
																	.filed(fields)
																	.fieldMessage(fieldMessages)
																	.build();
		
		return new ResponseEntity<>(beanValidationErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetailsBase> handleDefaultException(Exception ex, WebRequest request) {
		ErrorDetailsBase errorDetails = ErrorDetailsGeneral.builder().timestamp(new Date())
												.statusCode(HttpStatus.BAD_REQUEST.value())
												.message(ex.getMessage())
												.details(request.getDescription(false))
												.build();
				
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
}
