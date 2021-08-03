package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.repository.CuentaFBRepository;
import com.monitor.bankendmonitoreoLinks.service.ICuentaFBService;

@Service
public class CuentaFBServiceImp implements ICuentaFBService {
	@Autowired
	private CuentaFBRepository cuentafbRepository;

	@Override
	public List<CuentaFB> findAll() {

		return (List<CuentaFB>) cuentafbRepository.findAll();
	}

	@Override
	public CuentaFB save(CuentaFB cuentaFB) {

		return cuentafbRepository.save(cuentaFB);
	}

	@Override
	public void deleteById(String idCuenta) {
		cuentafbRepository.deleteById(idCuenta);

	}

}
