package com.monitor.bankendmonitoreoLinks.entity.admin;

import javax.persistence.*;

import com.monitor.bankendmonitoreoLinks.enums.RolNombre;
import com.sun.istack.NotNull;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(unique = true)
	private RolNombre rolNombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Rol(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Rol() {
	}
}
