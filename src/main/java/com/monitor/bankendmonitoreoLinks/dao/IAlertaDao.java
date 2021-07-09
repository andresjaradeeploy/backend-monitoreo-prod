package com.monitor.bankendmonitoreoLinks.dao;

import java.util.Date;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Alerta;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public interface IAlertaDao {

	public int generarAlerta(EstadoAnuncio estadoAnuncio,String fecha);
}
