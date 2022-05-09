package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;


import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;

public interface IEstadoLinkExternoService {

	
	public List<EstadoLinkExterno> obtenerAllEstados();
	public List<EstadoLinkExterno> findEstadoLinKExternobyCuenta(Long idCuenta);
}
