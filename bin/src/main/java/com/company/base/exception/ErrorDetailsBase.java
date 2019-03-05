package com.company.base.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDetailsBase {
	
	private Date timestamp;
	private int statusCode;
	
}
