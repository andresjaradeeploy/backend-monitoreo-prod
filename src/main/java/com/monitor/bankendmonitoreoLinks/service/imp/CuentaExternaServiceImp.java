package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Cuenta;
import com.monitor.bankendmonitoreoLinks.repository.ICuentaExternaRepository;
import com.monitor.bankendmonitoreoLinks.service.ICuentaExternaService;


@Service
public class CuentaExternaServiceImp implements ICuentaExternaService {
	
	@Autowired
	private ICuentaExternaRepository iCuentaExternaRepository;
	
	
	@Override
	public List<Cuenta> findAll() {
	return iCuentaExternaRepository.findAll();
	}

	@Override
	public Cuenta findById(Long id_cuenta) {
		return iCuentaExternaRepository.findById(id_cuenta).orElse(null);
	}

	@Override
	public Cuenta save(Cuenta cuenta) {
		return iCuentaExternaRepository.save(cuenta);
	}

	@Override
	public void deleteById(Long id_cuenta) {
		iCuentaExternaRepository.deleteById(id_cuenta);
		
	}

}
