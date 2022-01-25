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
	private String nameImage;
	
	
	public String getNameImage() {
		return nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Column(columnDefinition = "TEXT")
	private String picture;
	@Column(columnDefinition = "TEXT")
	private String permalink_url;
	
	
	

	public Integer getPost_engaged_fan() {
		return post_engaged_fan;
	}

	public void setPost_engaged_fan(Integer post_engaged_fan) {
		this.post_engaged_fan = post_engaged_fan;
	}

	public Integer getPost_engaged_users() {
		return post_engaged_users;
	}

	public void setPost_engaged_users(Integer post_engaged_users) {
		this.post_engaged_users = post_engaged_users;
	}

	public Integer getPost_negative_feedback() {
		return post_negative_feedback;
	}

	public void setPost_negative_feedback(Integer post_negative_feedback) {
		this.post_negative_feedback = post_negative_feedback;
	}

	public Integer getPost_clicks() {
		return post_clicks;
	}

	public void setPost_clicks(Integer post_clicks) {
		this.post_clicks = post_clicks;
	}

	public Integer getPost_clicks_unique() {
		return post_clicks_unique;
	}

	public void setPost_clicks_unique(Integer post_clicks_unique) {
		this.post_clicks_unique = post_clicks_unique;
	}
	
	private Integer likes;
	
	private Integer love;
	
	private Integer wow;
	
	private Integer haha;
	
	private Integer sorry;
	
	private Integer anger;
	
	

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}

	public Integer getWow() {
		return wow;
	}

	public void setWow(Integer wow) {
		this.wow = wow;
	}

	public Integer getHaha() {
		return haha;
	}

	public void setHaha(Integer haha) {
		this.haha = haha;
	}

	public Integer getSorry() {
		return sorry;
	}

	public void setSorry(Integer sorry) {
		this.sorry = sorry;
	}

	public Integer getAnger() {
		return anger;
	}

	public void setAnger(Integer anger) {
		this.anger = anger;
	}

	private Integer post_engaged_fan;
	
	private Integer post_engaged_users;
	
	private Integer post_negative_feedback;

	private Integer post_clicks;
	
	private Integer post_clicks_unique;
	
	
	
	
	
	private Integer shares;
	
	 @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private Page page;

}
