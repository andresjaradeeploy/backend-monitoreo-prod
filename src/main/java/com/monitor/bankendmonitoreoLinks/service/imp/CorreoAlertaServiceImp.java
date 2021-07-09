package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;
import com.monitor.bankendmonitoreoLinks.repository.ICorreoAlertaRepository;
import com.monitor.bankendmonitoreoLinks.service.ICorreoAlertaService;


@Service
public class CorreoAlertaServiceImp implements ICorreoAlertaService {

	@Autowired
	private ICorreoAlertaRepository iCorreoAlertaRepository;
	
	@Override
	public CorreoAlerta save(CorreoAlerta alerta) {
		
		return  iCorreoAlertaRepository.save(alerta);
	}

	@Override
	public List<CorreoAlerta> findAll() {
		
		return (List<CorreoAlerta>) iCorreoAlertaRepository.findAll();
	}

	
	@Override
	public List<CorreoAlerta> findCorreoAlertabycuentaFB(String idCuenta) {
		
		return (List<CorreoAlerta>) iCorreoAlertaRepository.findCorreoAlertabycuentaFB(idCuenta);
	}

	@Override
	public void borrarCorreo(Long idCorreo) {
		
		iCorreoAlertaRepository.deleteById(idCorreo);		
	}

}
