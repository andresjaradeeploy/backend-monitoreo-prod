package com.monitor.bankendmonitoreoLinks.dao;

import java.util.ArrayList;

public interface CuentaFBDao {
	
	
	 public boolean verificarSiExisteCuenta(String idCuenta);
	 public ArrayList<String> obtenerCuentasBD();
}
