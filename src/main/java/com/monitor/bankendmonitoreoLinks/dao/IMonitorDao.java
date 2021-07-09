package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Monitor;

public interface IMonitorDao {
	public long guardar();
	public long ultimoInsertado();
	public boolean verificarSiExisteMonitor(long idAnuncio) ;
	public List<Monitor> listarMonitores();
}
