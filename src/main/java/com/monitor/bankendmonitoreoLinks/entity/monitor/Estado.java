package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Estado implements Serializable {
	

	@Id
	@Column(name="id_estado")
	private int idEstado;
	
	@Column(name="nombre_estado")
	private  String nombreEstado;
	
	
	public int getIdEstado() {
		return idEstado;
	}


	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}


	public String getNombreEstado() {
		return nombreEstado;
	}


	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}


	private static final long serialVersionUID = 1L;

}
