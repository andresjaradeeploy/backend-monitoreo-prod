package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;

public interface ICorreoAlertaService {

	public CorreoAlerta save(CorreoAlerta alerta);

	public List<CorreoAlerta> findAll();

	public List<CorreoAlerta> findCorreoAlertabycuentaFB(String idCuenta);
	
	public void borrarCorreo(Long idCorreo);

}
