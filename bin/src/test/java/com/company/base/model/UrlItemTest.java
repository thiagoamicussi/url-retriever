package com.company.base.model;

import org.junit.Test;

public class UrlItemTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void givenNullUrl_whenCreateUrl_thenError() {
		@SuppressWarnings("unused")
		UrlItem url = new UrlItem(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenEmptyUrl_whenCreateUrl_thenError() {
		@SuppressWarnings("unused")
		UrlItem url = new UrlItem("");
	}

}
