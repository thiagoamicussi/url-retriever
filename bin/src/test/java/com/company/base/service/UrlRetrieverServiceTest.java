package com.company.base.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;
import com.company.base.repository.UrlRepository;

public class UrlRetrieverServiceTest {

	@Test
	public void givenAlreadySavedUrl_whenSave_thenReturnSameUrl() {

		UrlRepository urlRepository = mock(UrlRepository.class);
		UrlRetrieverService service = new UrlRetrieverService(urlRepository);
		
		Url url = mock(Url.class);
		when(urlRepository.findByUrlValue(url.getUrlValue())).thenReturn(1);
		
		Url returnUrl = service.save(url);
		
		assertTrue(returnUrl.equals(url));
	}
	
	@Test
	public void givenItensEmpty_whenSave_thenReturnSameUrl() {

		UrlRepository urlRepository = mock(UrlRepository.class);
		UrlRetrieverService service = new UrlRetrieverService(urlRepository);
		
		Url url = mock(Url.class);
		Set<UrlItem> items = new HashSet<UrlItem>();
		when(urlRepository.getUrlsFrom(url)).thenReturn(items);
		
		Url returnUrl = service.save(url);
		
		assertTrue(returnUrl.equals(url));
	}
	
}
