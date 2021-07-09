package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Monitor implements Serializable {
	

	@Id
	@Column(name="id_monitor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMonitor;

	
	/*@OneToMany(mappedBy = "monitor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EstadoAnuncio> estadoAnuncios;*/
	
	
/*
	public List<EstadoAnuncio> getEstadoAnuncios() {
		return estadoAnuncios;
	}



	public void setEstadoAnuncios(List<EstadoAnuncio> estadoAnuncios) {
		this.estadoAnuncios = estadoAnuncios;
	}

*/

	


	public long getIdMonitor() {
		return idMonitor;
	}



	public Monitor() {
		super();
	}



	public Monitor(long idMonitor) {
		super();
		this.idMonitor = idMonitor;
	}



	public void setIdMonitor(long idMonitor) {
		this.idMonitor = idMonitor;
	}






	private static final long serialVersionUID = 1L;

}
