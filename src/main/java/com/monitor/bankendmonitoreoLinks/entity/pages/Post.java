package com.monitor.bankendmonitoreoLinks.entity.pages;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	
	private Integer likes;
	
	private Integer love;
	
	private Integer wow;
	
	private Integer haha;
	
	private Integer sorry;
	
	private Integer anger;
	
	private Integer shares;
	
	private Integer post_impressions_unique;
	
	
	
	public Integer getPost_impressions_unique() {
		return post_impressions_unique;
	}

	public void setPost_impressions_unique(Integer post_impressions_unique) {
		this.post_impressions_unique = post_impressions_unique;
	}



	@Column(name = "fecha_hora", nullable = true)
	private LocalDateTime fechayHora;
	

	
	





	public LocalDateTime getFechayHora() {
		return fechayHora;
	}

	public void setFechayHora(LocalDateTime fechayHora) {
		this.fechayHora = fechayHora;
	}



	@Column(columnDefinition = "TEXT")
	private String full_picture;
	
	

	@Column(columnDefinition = "TEXT")
	private String nameImage;
	
	
	@Column(columnDefinition = "TEXT")
	private String picture;
	
	@Column(columnDefinition = "TEXT")
	private String permalink_url;
	
	
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


	
	
	
	
	
	
	
	 @ManyToOne(optional = true, fetch = FetchType.EAGER)
	    private Page page;

}
