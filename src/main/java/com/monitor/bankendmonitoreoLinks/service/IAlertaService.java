package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Alerta;



public interface IAlertaService {
	
	public List<Alerta> findAlertabycuentaFB(String idCuenta);

}
