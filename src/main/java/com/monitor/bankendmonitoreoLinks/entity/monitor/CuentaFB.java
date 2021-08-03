package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CuentaFB implements Serializable {

	@Id
	@Column(name = "id_cuentaFB")

	private String idCuenta;

	@Column(name = "nombre_cuenta")
	private String nombreCuenta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "cuenta_FB_developer")
	private CuentaFbDeveloper cuentaFbDeveloper;

	public CuentaFB(String idCuenta) {
		super();
		this.idCuenta = idCuenta;
	}

	public CuentaFB() {
		super();
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public CuentaFB(String idCuenta, String nombreCuenta, CuentaFbDeveloper cuentaFbDeveloper) {
		super();
		this.idCuenta = idCuenta;
		this.nombreCuenta = nombreCuenta;
		this.cuentaFbDeveloper = cuentaFbDeveloper;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public CuentaFbDeveloper getCuentaFbDeveloper() {
		return cuentaFbDeveloper;
	}

	public void setCuentaFbDeveloper(CuentaFbDeveloper cuentaFbDeveloper) {
		this.cuentaFbDeveloper = cuentaFbDeveloper;
	}

	private static final long serialVersionUID = 1L;

}
