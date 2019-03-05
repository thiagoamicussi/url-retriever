package com.company.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;

@Repository
public interface UrlItemRepository extends JpaRepository<UrlItem, Long>, UrlItemRepositoryCustom {

	public UrlItem findByUrl(Url url);
	
	@Override
	public void detach(UrlItem urlItem);
	
}
