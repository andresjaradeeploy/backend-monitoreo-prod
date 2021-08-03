package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Anuncio implements Serializable {

	@Id
	@Column(name = "id_anuncio")
	private String idAnuncio;

	private String nombre;

	private Integer impresiones;

	private String preview_shareable_link;

	public Integer getImpresiones() {
		return impresiones;
	}

	public void setImpresiones(Integer impresiones) {
		this.impresiones = impresiones;
	}

	public AdCreative getAdCreative() {
		return adCreative;
	}

	public void setAdCreative(AdCreative adCreative) {
		this.adCreative = adCreative;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuenta_fb")
	private CuentaFB cuentaFB;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adCreative")
	private AdCreative adCreative;

	public String getPreview_shareable_link() {
		return preview_shareable_link;
	}

	public void setPreview_shareable_link(String preview_shareable_link) {
		this.preview_shareable_link = preview_shareable_link;
	}

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

	public Anuncio(String idAnuncio, String nombre, CuentaFB cuentaFB) {
		super();
		this.idAnuncio = idAnuncio;
		this.nombre = nombre;
		this.cuentaFB = cuentaFB;
	}

	public Anuncio() {
		super();
	}

	private static final long serialVersionUID = 1L;

}
