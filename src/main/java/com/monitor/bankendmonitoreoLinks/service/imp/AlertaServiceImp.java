package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Alerta;
import com.monitor.bankendmonitoreoLinks.repository.IAlertaRepository;
import com.monitor.bankendmonitoreoLinks.service.IAlertaService;

@Service
public class AlertaServiceImp  implements IAlertaService{

	@Autowired
	private IAlertaRepository IAlertaRepository;
	
	@Override
	public List<Alerta> findAlertabycuentaFB(String idCuenta) {
		
		return (List<Alerta>) IAlertaRepository.findAlertabycuentaFB(idCuenta);
	}

}
