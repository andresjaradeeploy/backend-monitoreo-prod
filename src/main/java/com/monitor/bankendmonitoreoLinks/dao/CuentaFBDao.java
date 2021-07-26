package com.monitor.bankendmonitoreoLinks.dao;


import java.util.List;


import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;

public interface CuentaFBDao {
	
	public List<CuentaFB> consultar();
	
	 //public int guardar(CuentaFB cuentaFB);
	// public int actualizar();
	// public CuentaFB encontrar(CuentaFB cuentaFB);
	 public boolean verificarSiExisteCuenta(String idCuenta);

}
