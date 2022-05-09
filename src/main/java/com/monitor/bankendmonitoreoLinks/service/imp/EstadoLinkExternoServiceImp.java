package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;
import com.monitor.bankendmonitoreoLinks.repository.IEstadoLinkExternoRepository;
import com.monitor.bankendmonitoreoLinks.service.IEstadoLinkExternoService;


@Service
public class EstadoLinkExternoServiceImp  implements IEstadoLinkExternoService{

	@Autowired 
	private IEstadoLinkExternoRepository iEstadoLinkExternoRepository;
	
	@Override
	public List<EstadoLinkExterno> obtenerAllEstados() {
	return iEstadoLinkExternoRepository.findAll();
	}

	@Override
	public List<EstadoLinkExterno> findEstadoLinKExternobyCuenta(Long idCuenta) {
		return iEstadoLinkExternoRepository.findEstadoLinKExternobyCuenta(idCuenta);
	}

}
