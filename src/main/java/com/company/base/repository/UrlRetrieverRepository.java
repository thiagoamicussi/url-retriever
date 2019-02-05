package com.company.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;

@Repository
public interface UrlRetrieverRepository extends JpaRepository<Url, Long>, UrlRetrieverRepositoryCustom {

	@Query("select count(url) from Url url where urlValue = :urlValue")
	public int findByUrlValue(@Param("urlValue") String urlValue);

	@Query("select items from UrlItem items where url.id = :urlId")
	public List<UrlItem> findItemsByUrlId(long urlId);

}
