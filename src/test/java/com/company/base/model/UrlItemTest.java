package com.company.base.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UrlItemTest {
	
	@Test
    public void whenCalledSetUrlGivenTooBigValueThenTruncate() {
		String tooLongUrlValue = "dafadsfadsfdsafdsafdakjçlkjçlkjçlkjçjçjçjçjçkjçlkjçlkjçljkçlkadfadsfadsfjkdafiupiquerqyeruqewgfmioupupiupoupu";
		int tooLongUrlLength = tooLongUrlValue.length();
		UrlItem urlItem = new UrlItem(tooLongUrlValue);
		
		int truncatedUrlLength = urlItem.getUrlValue().length();
		
		assertTrue(truncatedUrlLength < tooLongUrlLength);
	}
	
	@Test
    public void whenCalledSetUrlGivenAcceptableValueNotTruncate() {
		String initialUrlValue = "dafadsfadsfdsafdsafdakjçlkjçlkjçlkjçjçjçjçjçkjçlkjçlkjçljkçlkadfadsfadsfjkdafiupiquerqyeruqewgfmi";
		int initialUrlLength = initialUrlValue.length();
		UrlItem urlItem = new UrlItem(initialUrlValue);
		
		int currentUrlLength = urlItem.getUrlValue().length();
		
		assertTrue(initialUrlLength == currentUrlLength);
	}

}
