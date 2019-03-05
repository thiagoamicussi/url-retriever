package com.company.base.repository;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;
/**
 * Adapter implementation for the third party HTLM parser
 *  
 * @author amicussi
 */
@Component
public class WebPageParserAdapter implements IWebPageParser {
	
	private static final int TRUNCATE_URL_LENTHG = 99;
	private static final long URL_LIMIT = 50;

	private Document getWebPage(String url) throws IllegalArgumentException {
		
		Document document = null;
		
		try {
			
			document = Jsoup.connect(url).userAgent("Mozilla").get();
			
		} catch (IOException e) {
			StringBuilder message = new StringBuilder("Invalid URL: ");
			message.append(url);
			throw new IllegalArgumentException(message.toString());
		}
		return document;
	}
	
	private boolean isUrlEmpty(Element element) {
		String url = extractUrl(element);
		
		return url.isEmpty();
	}

	private String extractUrl(Element element) {
		String url = element.attr("abs:href");
		if(url.isEmpty()) {
			element.attr("abs:src");
		}
		return truncateUrl(url);
	}

	private String truncateUrl(Element element) {
		String url = extractUrl(element);
		int lastIndex = getUrlLastIndex(url);

		return url.substring(0, lastIndex);
	}
	
	private String truncateUrl(String url) {
		int lastIndex = getUrlLastIndex(url);

		return url.substring(0, lastIndex);
	}

	private int getUrlLastIndex(String url) {
		int lastIndex = url.length();
		if(lastIndex >= TRUNCATE_URL_LENTHG) {
			lastIndex = TRUNCATE_URL_LENTHG; 
		}
		return lastIndex;
	}

	/**
	 * Return a list with links from the given parameter {@link url}
	 * Throws {@link IllegalArgumentException} in case parameter {@link url} is invalid
	 */
	@Override
	public Set<UrlItem> GetUrlsFrom(Url url) throws IllegalArgumentException{
		
		Document document = getWebPage(url.getUrlValue());
		
		Elements links = document.select("a[href], [src]");
		
		Set<UrlItem> urlList = links.stream()
						.filter(element -> !url.isSameUrl(extractUrl(element)))
						.filter(element -> !isUrlEmpty(element))
						.limit(URL_LIMIT)
						.map(element -> new UrlItem(truncateUrl(element)))
						.distinct()
						.collect(Collectors.toCollection(LinkedHashSet<UrlItem>::new));
		
		return urlList;
	}
}
