package com.company.base.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BeanValidationErrorDetails extends ErrorDetailsBase {
	
	private String filed;
	private String fieldMessage;
	
	@Builder
	public BeanValidationErrorDetails(Date timestamp, int statusCode, String filed, String fieldMessage) {
		super(timestamp, statusCode);
		this.filed = filed;
		this.fieldMessage = fieldMessage;
	}
}
