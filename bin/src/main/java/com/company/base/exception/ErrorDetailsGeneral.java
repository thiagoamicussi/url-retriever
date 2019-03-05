package com.company.base.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorDetailsGeneral extends ErrorDetailsBase {
	
	private String message;
	private String details;

	@Builder
	public ErrorDetailsGeneral(Date timestamp, int statusCode, String message, String details) {
		super(timestamp, statusCode);
		this.message = message;
		this.details = details;
	}
}
