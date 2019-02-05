package com.company.base.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;
import com.company.base.repository.UrlItemRepository;
import com.company.base.repository.UrlRetrieverRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class UrlRetrieverService extends ServiceAbstract { 
	
	private static int SAVED_URL_COUNTER = 0;
	
	@Autowired
	private UrlRetrieverRepository urlRepository;
	
	public UrlRetrieverService(UrlRetrieverRepository repository) {
		this.urlRepository = repository;
	}
	
	@Autowired
	private UrlItemRepository itemRepository;
	
	public UrlRetrieverService(UrlItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	private void setItemsData(Url url, Set<UrlItem> items) {
		for (UrlItem item: items) {
			item.setUrl(url);
			itemRepository.save(item);
		}
	}
	
	private boolean isUrlAlreadySaved(Url url) {
		if(urlRepository.findByUrlValue(url.getUrlValue()) > 0) {
			return true;
		}
		
		return false;
	}
	
	
	public Url save(Url url) {
		
		if (isUrlAlreadySaved(url)) {
			return url;
		}
		
		Set<UrlItem> items = urlRepository.getUrlsFrom(url);
		if(items.isEmpty()) return url;
	    
		Iterator<UrlItem> it = items.iterator();
	    while(it.hasNext()) {
	    	urlRepository.save(url);
	    	setItemsData(url, items);	    	
	    	
	    	SAVED_URL_COUNTER++;
	    	if(SAVED_URL_COUNTER >= 50) {
				break;
			}
	    	
	    	UrlItem nextUrlItem = it.next();
	    	
	    	if(nextUrlItem.getUrlValue().isEmpty()) continue;
	    	
	    	Url nextUrl = new Url(nextUrlItem.getUrlValue()); 
	    	save(nextUrl);
	    }
	    return url;
	}

	public Optional<Url> findById(long id) {
		return urlRepository.findById(id);
	}

	public List<Url> findAll() {
		return urlRepository.findAll();
	}

	public void delete(Url url) {
		urlRepository.delete(url);
	}

	public List<UrlItem> findItemsByUrlId(long id) {
		return urlRepository.findItemsByUrlId(id);
	}
}
