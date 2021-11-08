package com.monitor.bankendmonitoreoLinks.entity.pages;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String idPage;

	private String name;
	
	private String social_sentence;

	@Column(columnDefinition = "TEXT")
	private String access_token;

	public String getIdPage() {
		return idPage;
	}

	public String getSocial_sentence() {
		return social_sentence;
	}

	public void setSocial_sentence(String social_sentence) {
		this.social_sentence = social_sentence;
	}

	public void setIdPage(String idPage) {
		this.idPage = idPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getFan_count() {
		return fan_count;
	}

	public void setFan_count(Integer fan_count) {
		this.fan_count = fan_count;
	}

	@Column(columnDefinition = "TEXT")
	private String link;
	
	@Column(columnDefinition = "TEXT")
	private String cover;
	

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	private String category;

	@Column(columnDefinition = "TEXT")
	private String picture;

	private Integer fan_count;

}
