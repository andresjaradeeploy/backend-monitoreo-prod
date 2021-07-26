package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public interface IEstadoAnuncio {

	public int guardar(EstadoAnuncio estadoAnuncio, Anuncio anuncio);

	public List<EstadoAnuncio> listarAnuncios();

	public boolean verificarSiExisteEstadoAnuncio(String anuncio);

	public int actualizar(EstadoAnuncio estadoAnuncio, Estado estado);

	public List<EstadoAnuncio> obtener();

}
