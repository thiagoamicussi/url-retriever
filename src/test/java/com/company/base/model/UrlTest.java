package com.company.base.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UrlTest {
	
	@Test
    public void whenCalledSetUrlGivenTooBigValueThenTruncate() {
		String tooLongUrlValue = "dafadsfadsfdsafdsafdakjçlkjçlkjçlkjçjçjçjçjçkjçlkjçlkjçljkçlkadfadsfadsfjkdafiupiquerqyeruqewgfmioupupiupoupu";
		int tooLongUrlLength = tooLongUrlValue.length();
		Url url = new Url(tooLongUrlValue);
		
		int truncatedUrlLength = url.getUrlValue().length();
		
		assertTrue(truncatedUrlLength < tooLongUrlLength);
	}
	
	@Test
    public void whenCalledSetUrlGivenAcceptableValueNotTruncate() {
		String initialUrlValue = "dafadsfadsfdsafdsafdakjçlkjçlkjçlkjçjçjçjçjçkjçlkjçlkjçljkçlkadfadsfadsfjkdafiupiquerqyeruqewgfmi";
		int initialUrlLength = initialUrlValue.length();
		Url url = new Url(initialUrlValue);
		
		int currentUrlLength = url.getUrlValue().length();
		
		assertTrue(initialUrlLength == currentUrlLength);
	}

}
