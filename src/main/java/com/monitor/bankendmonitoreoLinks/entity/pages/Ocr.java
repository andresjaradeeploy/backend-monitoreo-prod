package com.monitor.bankendmonitoreoLinks.entity.pages;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;


@Entity
public class Ocr implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOcr;
	
	@Column(columnDefinition = "TEXT")
	private String descriptionOcr;
	
	
	
	 @OneToOne()
		@JoinColumn(name = "post", updatable = true, nullable = true)
	 private Post post;
	
	
	
	public Long getIdOcr() {
		return idOcr;
	}





	public void setIdOcr(Long idOcr) {
		this.idOcr = idOcr;
	}





	public String getDescriptionOcr() {
		return descriptionOcr;
	}





	public void setDescriptionOcr(String descriptionOcr) {
		this.descriptionOcr = descriptionOcr;
	}





	public Post getPost() {
		return post;
	}





	public void setPost(Post post) {
		this.post = post;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	

}
