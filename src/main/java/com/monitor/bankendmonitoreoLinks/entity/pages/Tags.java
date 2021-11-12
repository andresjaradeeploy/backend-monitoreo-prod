package com.monitor.bankendmonitoreoLinks.entity.pages;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tags implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTag;
	
	private String nameTag;
	
	 @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private Post post;

	public Long getIdTag() {
		return idTag;
	}

	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}

	public String getNameTag() {
		return nameTag;
	}

	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	

}
