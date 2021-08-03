package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public interface IEstadoAnuncioService {
	public List<EstadoAnuncio> findAll();

	public List<EstadoAnuncio> findEstadoAnunciobycuentaFB(String idCuenta);
}
