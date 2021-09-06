package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;

public interface IAnuncioDao {
	
	public int guardar(Anuncio anuncio,CuentaFB cuentaFB, AdCreative adCreative);
	public List<Anuncio> listarAnuncios();
	public boolean verificarSiExisteAnuncio(String idAnuncio);
	public int actualizar(Anuncio anuncio);
}
