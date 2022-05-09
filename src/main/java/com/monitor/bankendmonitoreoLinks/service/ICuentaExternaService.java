package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Cuenta;

public interface ICuentaExternaService {
	
	public List<Cuenta> findAll();

	public Cuenta findById(Long id_cuenta);

	public Cuenta save(Cuenta cuenta);

	public void deleteById(Long id_cuenta);
}
