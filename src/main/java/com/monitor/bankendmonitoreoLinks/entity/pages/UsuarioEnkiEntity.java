package com.monitor.bankendmonitoreoLinks.entity.pages;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsuarioEnkiEntity implements Serializable{

	
	@Id
	private String idUser;
	
	private String nombreUser;
	
	private boolean autorizado;
	
	
	
	
	public UsuarioEnkiEntity() {
		super();
	}


	public String getIdUser() {
		return idUser;
	}


	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}


	public String getNombreUser() {
		return nombreUser;
	}


	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}


	public boolean isAutorizado() {
		return autorizado;
	}


	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
