package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class CuentaFbDeveloper implements Serializable {

	@Id
	@Column(name = "id_cuenta")
	private int idCuenta;

	@Column(name = "access_token")
	private String accessToken;

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public List<CuentaFB> getCuentaFB() {
		return cuentaFB;
	}

	public void setCuentaFB(List<CuentaFB> cuentaFB) {
		this.cuentaFB = cuentaFB;
	}

	@Column(name = "id_aplicacion")
	private String idAplicacion;

	@Column(name = "secret_key")
	private String secretKey;

	@OneToMany(mappedBy = "cuentaFbDeveloper", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CuentaFB> cuentaFB;

	private static final long serialVersionUID = 1L;

	public CuentaFbDeveloper(int idCuenta) {
		super();
		this.idCuenta = idCuenta;
	}

	public CuentaFbDeveloper() {
		// TODO Auto-generated constructor stub
	}
}
