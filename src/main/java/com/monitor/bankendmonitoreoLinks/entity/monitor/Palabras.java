package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Palabras implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPalabra;
	
	private String palabra;

	 @ManyToOne(optional = true, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	    private Anuncio anuncio;
	 
	 
	 @ManyToOne(optional = true, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	    private LinkExterno linkExterno;
	
	
	public Long getIdPalabra() {
		return idPalabra;
	}

	public void setIdPalabra(Long idPalabra) {
		this.idPalabra = idPalabra;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

}
