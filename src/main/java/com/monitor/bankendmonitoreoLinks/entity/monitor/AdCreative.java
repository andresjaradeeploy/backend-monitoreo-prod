package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AdCreative implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long idCreative;
	
	private String link;
	
	public Long getIdCreative() {
		return idCreative;
	}

	public void setIdCreative(Long idCreative) {
		this.idCreative = idCreative;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public AdCreative(String link, String urlImg, Anuncio anuncio) {
		super();
		this.link = link;
		this.urlImg = urlImg;
		this.anuncio = anuncio;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public AdCreative() {
		super();
	}

	public AdCreative(Long idCreative, String link, String urlImg, Anuncio anuncio) {
		super();
		this.idCreative = idCreative;
		this.link = link;
		this.urlImg = urlImg;
		this.anuncio = anuncio;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	private String urlImg;
	
	
	@OneToOne
	@JoinColumn(name = "adCreative")
	private AdCreative adCreative;
	
	
	@OneToOne
	@JoinColumn(name = "anuncio")
	private Anuncio anuncio;

}
