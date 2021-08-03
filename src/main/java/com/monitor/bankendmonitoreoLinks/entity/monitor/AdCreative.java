package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdCreative implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long idCreative;

	@Column(columnDefinition = "TEXT")
	private String link;

	@Column(name = "nombre_anuncio")
	private String nombre;

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

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AdCreative() {
		super();
	}

	public AdCreative(Long idCreative, String link, String nombre, String urlImg) {
		super();
		this.idCreative = idCreative;
		this.link = link;
		this.nombre = nombre;
		this.urlImg = urlImg;

	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	@Column(columnDefinition = "TEXT")
	private String urlImg;

	public AdCreative(Long idCreative) {
		super();
		this.idCreative = idCreative;
	}

}
