package com.company.base.repository;

import java.util.Set;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;

public interface IWebPageParser {
	public Set<UrlItem> GetUrlsFrom(Url url);
}
