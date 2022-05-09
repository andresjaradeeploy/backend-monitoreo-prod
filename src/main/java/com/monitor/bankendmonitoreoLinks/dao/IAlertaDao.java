package com.monitor.bankendmonitoreoLinks.dao;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;

public interface IAlertaDao {

	public int generarAlerta(EstadoAnuncio estadoAnuncio, String fecha);
	public int generarAlertaExterno(EstadoLinkExterno estadoLinkExterno, String fecha);
}
