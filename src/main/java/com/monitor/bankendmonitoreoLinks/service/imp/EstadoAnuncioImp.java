package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.repository.IEstadoAnuncioRepository;
import com.monitor.bankendmonitoreoLinks.service.IEstadoAnuncioService;

@Service
public class EstadoAnuncioImp implements IEstadoAnuncioService {

	@Autowired
	private IEstadoAnuncioRepository IEstadoAnuncioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<EstadoAnuncio> findAll() {
		return (List<EstadoAnuncio>) IEstadoAnuncioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadoAnuncio> findEstadoAnunciobycuentaFB(String idCuenta) {
		return (List<EstadoAnuncio>) IEstadoAnuncioRepository.findAlertabycuentaFB(idCuenta);
	}

}
