package com.company.base.repository.integration;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;
import com.company.base.repository.IWebPageParser;
import com.company.base.repository.WebPageParserAdapter;

public class WebPageParserAdapterTest {
	
	IWebPageParser webAdapter;
	Set<UrlItem> urls;
	
	@Before
	public void init() {
		webAdapter = new WebPageParserAdapter();
		urls = webAdapter.GetUrlsFrom(new Url("http://google.com"));
	}
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void givenMalformedURL_whenGetUrlsFrom_thenError() {
		
		IWebPageParser webAdapter = new WebPageParserAdapter();
		
		@SuppressWarnings("unused")
		Set<UrlItem> urls = webAdapter.GetUrlsFrom(new Url("http://urlmalformada.com"));
	}
	
	@Test
	public void givenCorrectUrl_whenGetUrlsFrom_thenReturnItems() {
				
		assertTrue(this.urls.size() > 0);
	}
	
	@Test
	public void givenCorrectUrl_whenGetUrlsFrom_thenReturnUpTo50Itens() {
		IWebPageParser webAdapter = new WebPageParserAdapter();
		Set<UrlItem> urls = webAdapter.GetUrlsFrom(new Url("http://google.com"));
		
		assertTrue(urls.size() > 0);
		assertTrue(urls.size() <= 50);
	}
	
}
