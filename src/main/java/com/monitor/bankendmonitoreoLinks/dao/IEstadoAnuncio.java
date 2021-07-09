package com.monitor.bankendmonitoreoLinks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public interface IEstadoAnuncio {
	
	public int guardar(EstadoAnuncio estadoAnuncio, Anuncio anuncio);
    public List<EstadoAnuncio> listarAnuncios();
    public boolean verificarSiExisteEstadoAnuncio(String idAnuncio);
    public int actualizar(EstadoAnuncio estadoAnuncio,Estado estado);
    public List<EstadoAnuncio> obtener();
}
