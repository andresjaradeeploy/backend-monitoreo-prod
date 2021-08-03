package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;

public interface ICuentaFBService {

	public List<CuentaFB> findAll();

	public CuentaFB save(CuentaFB cuentaFB);

	public void deleteById(String idCuenta);
}
