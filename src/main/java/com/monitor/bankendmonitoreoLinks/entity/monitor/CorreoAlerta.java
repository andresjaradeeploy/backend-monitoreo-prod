package com.monitor.bankendmonitoreoLinks.entity.monitor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class CorreoAlerta implements Serializable {

	@Id
	@Column(name = "id_correo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCorreo;

	@Column(name = "cuenta_correo")
	private String cuentaCorreo;

	public long getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(long idCorreo) {
		this.idCorreo = idCorreo;
	}

	public String getCuentaCorreo() {
		return cuentaCorreo;
	}

	public void setCuentaCorreo(String cuentaCorreo) {
		this.cuentaCorreo = cuentaCorreo;
	}

	public Alerta getAlerta() {
		return alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public CuentaFB getCuentaFB() {
		return cuentaFB;
	}

	public void setCuentaFB(CuentaFB cuentaFB) {
		this.cuentaFB = cuentaFB;
	}

	@ManyToOne()
	@JoinColumn(name = "alerta")
	private Alerta alerta;

	@ManyToOne()
	@JoinColumn(name = "cuenta_fb")
	private CuentaFB cuentaFB;

	private static final long serialVersionUID = 1L;

}
