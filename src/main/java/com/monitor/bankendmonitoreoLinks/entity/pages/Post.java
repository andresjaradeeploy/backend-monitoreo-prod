package com.monitor.bankendmonitoreoLinks.entity.pages;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Post implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	private String idPost;
	
	
	private String created_time;
	
	@Column(columnDefinition = "TEXT")
	private String message;
	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFull_picture() {
		return full_picture;
	}

	public void setFull_picture(String full_picture) {
		this.full_picture = full_picture;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPermalink_url() {
		return permalink_url;
	}

	public void setPermalink_url(String permalink_url) {
		this.permalink_url = permalink_url;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	@Column(columnDefinition = "TEXT")
	private String full_picture;
	@Column(columnDefinition = "TEXT")
	private String picture;
	@Column(columnDefinition = "TEXT")
	private String permalink_url;
	
	private Integer shares;

}
