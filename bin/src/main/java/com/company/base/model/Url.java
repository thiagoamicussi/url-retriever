package com.company.base.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited
@Entity
@Table(indexes = {@Index(name = "idxUrlValue", columnList="urlValue", unique = true)})
public class Url extends EntityAbstract implements IUrlValidator{
	
	@Getter
	private String urlValue;
		
	@Builder.Default
	@OneToMany(mappedBy = "url", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UrlItem> items = new HashSet<>();
	
	public Url(String urlValue) {
		validateUrl(urlValue);
		this.urlValue = urlValue;
	}

	public Url(long id, String urlValue) {
		this.urlValue = urlValue;
	}
	
	public void add(UrlItem urlItem) {
		this.items.add(urlItem);
	}
	
	public void setUrlValue(String urlValue) {
		this.urlValue = urlValue;
	}

	public boolean isSameUrl(String url) {
		return this.urlValue.equals(url);
	}
}
