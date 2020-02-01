package com.tiny.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "TINY_URL")
@Table(name = "TINY_URL")
public class TinyUrlEB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long tinyUrlId;
	private String url;
	private String tinyUrl;
	private java.sql.Timestamp entryDate;
	private String entryIP;
	private String status;
	private Integer hitCount;
	private String deleted;
	

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "TINY_URL_ID")
	@JsonIgnore
	public Long getTinyUrlId() {
		return tinyUrlId;
	}

	public void setTinyUrlId(Long tinyUrlId) {
		this.tinyUrlId = tinyUrlId;
	}
	
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "TINY_URL")
	public String getTinyUrl() {
		return tinyUrl;
	}
	
	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ENTRY_DATE")
	@JsonIgnore
	public java.sql.Timestamp getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(java.sql.Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "ENTRY_IP")
	@JsonIgnore
	public String getEntryIP() {
		return entryIP;
	}

	public void setEntryIP(String entryIP) {
		this.entryIP = entryIP;
	}

	@Column(name = "HIT_COUNT")
	@JsonIgnore
	public Integer getHitCount() {
		return hitCount;
	}

	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}

	@Column(name = "DELETED")
	@JsonIgnore
	public String getDeleted() {
		return deleted;
	}
	
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
