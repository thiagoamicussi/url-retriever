package com.company.base.repository;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;

@Repository
public class UrlRepositoryImpl implements UrlRepositoryCustom {

	@Autowired
	WebPageParserAdapter adapter;
	
	@Override
	public Set<UrlItem> getUrlsFrom(Url url) {
		return adapter.GetUrlsFrom(url);
		
	}

}
