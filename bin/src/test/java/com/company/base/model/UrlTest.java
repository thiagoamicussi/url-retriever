package com.company.base.model;

import org.junit.Test;

public class UrlTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void givenNullUrl_whenCreateUrl_thenError() {
		@SuppressWarnings("unused")
		Url url = new Url(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenEmptyUrl_whenCreateUrl_thenError() {
		@SuppressWarnings("unused")
		Url url = new Url("");
	}
	
}
