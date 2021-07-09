package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Anuncio implements Serializable {

	@Id
	@Column(name = "id_anuncio")
	private String idAnuncio;

	@Column(name = "link_anuncio")
	private String linkAnuncio;
	
	private String nombre;

	@OneToOne()
	@JoinColumn(name = "cuenta_fb")
	private CuentaFB cuentaFB;

	public String getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(String idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLinkAnuncio() {
		return linkAnuncio;
	}

	public void setLinkAnuncio(String linkAnuncio) {
		this.linkAnuncio = linkAnuncio;
	}

	public CuentaFB getCuentaFB() {
		return cuentaFB;
	}

	public void setCuentaFB(CuentaFB cuentaFB) {
		this.cuentaFB = cuentaFB;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Anuncio(String idAnuncio, String nombre) {
		super();
		this.idAnuncio = idAnuncio;
		this.nombre = nombre;
	}
	

	public Anuncio(String idAnuncio, String linkAnuncio, String nombre, CuentaFB cuentaFB) {
		super();
		this.idAnuncio = idAnuncio;
		this.linkAnuncio = linkAnuncio;
		this.nombre = nombre;
		this.cuentaFB = cuentaFB;
	}

	public Anuncio() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
}
