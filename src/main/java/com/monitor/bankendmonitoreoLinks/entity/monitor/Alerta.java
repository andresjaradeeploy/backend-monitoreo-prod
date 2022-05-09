package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Alerta implements Serializable {

	@Id
	@Column(name = "id_alerta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlerta;

	@Column(name = "fecha_hora")
	private String fechaAlerta;

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "estado_anuncio_fk", updatable = false, nullable = true)
	private EstadoAnuncio estadoAnuncio;
	
	@OneToOne
	@JoinColumn(name = "estado_link_Externo", updatable = false, nullable = true)
	private EstadoLinkExterno estadoLinkExterno;

	@OneToMany(mappedBy = "alerta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CorreoAlerta> correosDeAlerta;

	public List<CorreoAlerta> getCorreosDeAlerta() {
		return correosDeAlerta;
	}

	public void setCorreosDeAlerta(List<CorreoAlerta> correosDeAlerta) {
		this.correosDeAlerta = correosDeAlerta;
	}

	public Alerta(String fechaAlerta, EstadoAnuncio estadoAnuncio, List<CorreoAlerta> correosDeAlerta) {
		super();
		this.fechaAlerta = fechaAlerta;
		this.estadoAnuncio = estadoAnuncio;
		this.correosDeAlerta = correosDeAlerta;
	}

	public Long getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}

	public String getFechaAlerta() {
		return fechaAlerta;
	}

	public void setFechaAlerta(String fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}

	public EstadoAnuncio getEstadoAnuncio() {
		return estadoAnuncio;
	}

	public void setEstadoAnuncio(EstadoAnuncio estadoAnuncio) {
		this.estadoAnuncio = estadoAnuncio;
	}

	public Alerta() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EstadoLinkExterno getEstadoLinkExterno() {
		return estadoLinkExterno;
	}

	public void setEstadoLinkExterno(EstadoLinkExterno estadoLinkExterno) {
		this.estadoLinkExterno = estadoLinkExterno;
	}

}
