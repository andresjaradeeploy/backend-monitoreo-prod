package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;
import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;



public interface IEstadoLinkExterno {
	
	
	public int guardar(EstadoLinkExterno estadoLinkExterno, LinkExterno linkExterno);

	public List<EstadoLinkExterno> listarEstadosLinks();

	public boolean verificarSiExisteEstadoLinkExterno(Long LinkExterno);

	public int actualizar(EstadoLinkExterno estadoLinkExterno, Estado estado);
	public List<EstadoLinkExterno> obtenerEstadosExternos();

}
