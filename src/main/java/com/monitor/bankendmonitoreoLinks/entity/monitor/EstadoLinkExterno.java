package com.monitor.bankendmonitoreoLinks.entity.monitor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class EstadoLinkExterno {

	@OneToOne
	@JoinColumn(name = "link_externo", updatable = false, nullable = false)
	private LinkExterno link_externo;

	@Column(columnDefinition = "TEXT")
	private String title;

	@Column(name = "meta_description", columnDefinition = "TEXT")
	private String metaDescription;

	@Column(name = "resultado_busqueda", columnDefinition = "TEXT")
	private String resultadoBusquedaPalabras;

	private String mensaje;

	private String correoAlerta;
	
	@Column(name = "code_status")
	private Integer code = 0;
	
	@OneToOne()
	@JoinColumn(name = "estado", updatable = false, nullable = true)
	private Estado estado;

	public LinkExterno getLink_externo() {
		return link_externo;
	}

	public void setLink_externo(LinkExterno link_externo) {
		this.link_externo = link_externo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getResultadoBusquedaPalabras() {
		return resultadoBusquedaPalabras;
	}

	public void setResultadoBusquedaPalabras(String resultadoBusquedaPalabras) {
		this.resultadoBusquedaPalabras = resultadoBusquedaPalabras;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCorreoAlerta() {
		return correoAlerta;
	}

	public void setCorreoAlerta(String correoAlerta) {
		this.correoAlerta = correoAlerta;
	}
	
	
	

}
