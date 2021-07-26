package com.monitor.bankendmonitoreoLinks.dao;



import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public interface IAlertaDao {

	public int generarAlerta(EstadoAnuncio estadoAnuncio, String fecha);
}
