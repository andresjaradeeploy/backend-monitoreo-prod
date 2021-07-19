package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;

public interface IAnuncioService {

	public List<Anuncio> findAll();
	public Anuncio findById(String id_anuncio);
	public Anuncio save(Anuncio anuncio);
	public void deleteById(String id_anuncio);
	public List<Anuncio> findAnunciobyCuentaFB(String cuentaFB);
}
