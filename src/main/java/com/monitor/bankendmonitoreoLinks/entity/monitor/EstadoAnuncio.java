package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class EstadoAnuncio implements Serializable {

	@Id
	@Column(name = "id_estado_anuncio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEstadoAnuncio;

	@Column(columnDefinition = "TEXT")
	private String title;

	@Column(name = "meta_description",columnDefinition = "TEXT")
	private String metaDescription;

	private String mensaje;

	@Column(name = "code_status")
	private Integer code = 0;

	@PrePersist
	public void prePersist() {
		code = 0;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

	public EstadoAnuncio() {
		super();
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	@OneToOne()
	@JoinColumn(name = "estado", updatable = false, nullable = true)
	private Estado estado;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "anuncio", updatable = false, nullable = false)
	private Anuncio anuncio;

	public long getIdEstadoAnuncio() {
		return idEstadoAnuncio;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public void setIdEstadoAnuncio(long idEstadoAnuncio) {
		this.idEstadoAnuncio = idEstadoAnuncio;
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

	private static final long serialVersionUID = 1L;

	public EstadoAnuncio(long idEstadoAnuncio, String title, String metaDescription, Estado estado, Anuncio anuncio) {
		super();
		this.idEstadoAnuncio = idEstadoAnuncio;
		this.title = title;
		this.metaDescription = metaDescription;
		this.estado = estado;

	}

}
