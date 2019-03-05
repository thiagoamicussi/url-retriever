package com.company.base.model;

public interface IUrlValidator {

	default void validateUrl(String urlValue) {
		if(urlValue == null || urlValue.isEmpty()) {
			throw new IllegalArgumentException("Invalid URL");
		}
	}
}
