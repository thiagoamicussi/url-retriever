package com.company.base.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited
@Entity
@Table(indexes = {@Index(name = "idxUrlIdUrlValue", columnList="url_id,urlValue", unique = true),
				  @Index(name = "idxUrl", columnList="urlValue")})
public class UrlItem extends EntityAbstract {
	
	private String urlValue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "url_id", nullable = false)
	private Url url;

	public UrlItem(String urlValue) {
		super();
		this.urlValue = Url.urlTruncate(urlValue);
	}
	
	public void setUrlValue(String urlValue) {
		
		this.urlValue = Url.urlTruncate(urlValue);
	}
}
