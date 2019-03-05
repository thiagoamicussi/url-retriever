package com.company.base.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.base.model.UrlItem;

@Repository
public class UrlItemRepositoryImpl implements UrlItemRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	WebPageParserAdapter adapter;
	
	@Override
	public void detach(UrlItem urlItem) {
		entityManager.detach(urlItem);		
	}

}
